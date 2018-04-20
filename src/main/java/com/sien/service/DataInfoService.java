package com.sien.service;

import com.sien.constants.DocumentNodeContant;
import com.sien.db.dao.CommonDAO;
import com.sien.entity.CommonColumn;
import com.sien.entity.DataInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Slf4j
@Component
public class DataInfoService {

    @Autowired
    private CommonDAO commonDAO;

    private static final String MYSQL_PRI = "PRI";

    private static final String DEFAULT_IMPORT_INFO_FILE = "dataImport.xml";

    public List<DataInfo> parseImportInfo(String fileName) {
        String name = StringUtils.isBlank(fileName) ? DEFAULT_IMPORT_INFO_FILE : fileName;
        List<DataInfo> dataInfoList = new ArrayList<>();

        try(InputStream inputStream = DataInfoService.class.getClassLoader().getResourceAsStream(name)) {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element data = document.getRootElement();
            Iterator it = data.elementIterator();
            while (it.hasNext()) {
                Element index = (Element) it.next();
                Attribute indexName = index.attribute("name");
                if(indexName == null || indexName.getValue() == null) {
                    log.warn("no index name found..");
                    continue;
                }

                Element type = index.element(DocumentNodeContant.ELEMENT_TYPE);
                Element tableName = index.element(DocumentNodeContant.ELEMENT_TABLE_NAME);
                Element importSql = index.element(DocumentNodeContant.ELEMENT_IMPORT_SQL);

                if(type == null || type.getData() == null) {
                    log.warn("no type data found...");
                    continue;
                }

                if(tableName == null || tableName.getData() == null) {
                    log.warn("no tableName data found...");
                    continue;
                }

                if(importSql == null || importSql.getData() == null) {
                    log.warn("no importSql data found...");
                    continue;
                }

                List<CommonColumn> commonColumns = commonDAO.getColumns(tableName.getData().toString());
                if(CollectionUtils.isEmpty(commonColumns)) {
                    log.warn("table " + tableName.getData().toString() + " not exists");
                    continue;
                }

                Optional<CommonColumn> optionalCommonColumn = commonColumns.stream()
                        .filter(o -> StringUtils.isNotBlank(o.getKey()) && o.getKey().equalsIgnoreCase(MYSQL_PRI))
                        .findFirst();

                if(!optionalCommonColumn.isPresent()) {
                    log.error("table " + tableName + " cont not get a primary key");
                    continue;
                }

                dataInfoList.add(DataInfo.builder()
                        .index(indexName.getValue())
                        .type(type.getData().toString())
                        .tableName(tableName.getData().toString())
                        .importSql(importSql.getData().toString())
                        .primaryKey(optionalCommonColumn.get().getField())
                        .commonColumns(commonColumns)
                        .build());

            }

            return dataInfoList;
        } catch (Exception e) {
            log.error("failed to parse table info", e);
        }

        return Collections.emptyList();
    }

    public List<Map<String, Object>> getData(DataInfo dataInfo) {
        String sql = dataInfo.getImportSql();
        if(StringUtils.isBlank(sql)) {
            log.warn("table " + dataInfo.getTableName() + " not data  need to import : no sql found");
            return Collections.emptyList();
        }

        List<Map<String, Object>> maps = commonDAO.getForMap(dataInfo.getImportSql());
        if(CollectionUtils.isEmpty(maps)) {
            log.warn("table " + dataInfo.getTableName() + " not data  need to import : no data found");
            return Collections.emptyList();
        }

        return this.resultHandle(maps, dataInfo);
    }

    private List<Map<String, Object>> resultHandle(List<Map<String, Object>> maps, DataInfo dataInfo) {
        // simple version : direct import..

        return maps;
    }
}