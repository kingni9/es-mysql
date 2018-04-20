package com.sien.service;

import com.sien.db.dao.CommonDAO;
import com.sien.entity.CommonColumn;
import com.sien.entity.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Slf4j
@Component
public class TableInfoService {

    @Autowired
    private CommonDAO commonDAO;

    private static final String MYSQL_PRI = "PRI";

    private static final String DEFAULT_IMPORT_INFO_FILE = "importInfo.properties";

    public List<Map<String, Object>> getData(TableInfo tableInfo) {
        String sql = tableInfo.getSql();
        if(StringUtils.isBlank(sql)) {
            log.warn("table " + tableInfo.getTableName() + " not data  need to import : no sql found");
            return Collections.emptyList();
        }

        List<Map<String, Object>> maps = commonDAO.getForMap(tableInfo.getSql());
        if(CollectionUtils.isEmpty(maps)) {
            log.warn("table " + tableInfo.getTableName() + " not data  need to import : no data found");
            return Collections.emptyList();
        }

        return this.resultHandle(maps, tableInfo);
    }

    private List<Map<String, Object>> resultHandle(List<Map<String, Object>> maps, TableInfo tableInfo) {
        // simple version : direct import..

        return maps;
    }

    public List<TableInfo> parseImportInfo(String fileName) {
        String name = StringUtils.isBlank(fileName) ? DEFAULT_IMPORT_INFO_FILE : fileName;

        try(InputStream inputStream = TableInfoService.class.getClassLoader().getResourceAsStream(name)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            if(properties.isEmpty()) {
                log.warn("the " + fileName + "is empty");
                return Collections.emptyList();
            }

            return this.generateTableInfo(properties);
        } catch (Exception e) {
            log.error("failed to parse table info", e);
        }

        return Collections.emptyList();
    }

    private List<TableInfo> generateTableInfo(Properties properties) {
        List<TableInfo> tableInfo = new ArrayList<>();

        Set<Map.Entry<Object, Object>> entries = properties.entrySet();

        for(Map.Entry<Object, Object> entry : entries) {
            String tableName = entry.getKey().toString();
            List<CommonColumn> commonColumns = commonDAO.getColumns(tableName);

            if(CollectionUtils.isEmpty(commonColumns)) {
                log.warn("table " + tableName + " not exists");
                continue;
            }

            Optional<CommonColumn> optionalCommonColumn = commonColumns.stream()
                    .filter(o -> StringUtils.isNotBlank(o.getKey()) && o.getKey().equalsIgnoreCase(MYSQL_PRI))
                    .findFirst();

            if(!optionalCommonColumn.isPresent()) {
                log.error("table " + tableName + " cont not get a primary key");
                continue;
            }

            tableInfo.add(TableInfo.builder()
                    .tableName(tableName)
                    .sql(entry.getValue().toString())
                    .primaryKey(optionalCommonColumn.get().getField())
                    .commonColumns(commonColumns)
                    .build());
        }

        return tableInfo;
    }
}