package com.sien.service;

import com.alibaba.fastjson.JSON;
import com.sien.entity.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Component
@Slf4j
public class DataImportExecutor {
    @Autowired
    private ElasticsearchService elasticsearchService;
    @Autowired
    private TableInfoService tableInfoService;

    public void dataImport(String fileName) {
        List<TableInfo> tableInfoList = tableInfoService.parseImportInfo(fileName);

        if(!CollectionUtils.isEmpty(tableInfoList)) {
            tableInfoList.stream().forEach(o -> {
                List<Map<String, Object>> maps = tableInfoService.getData(o);
                this.indexStore(o, maps);
            });
        }

    }

    private void indexStore(TableInfo tableInfo, List<Map<String, Object>> maps) {
        maps.stream().forEach(o -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setIndexName("main");
            indexQuery.setType("principal");
            indexQuery.setId(o.get(tableInfo.getPrimaryKey()).toString());
            indexQuery.setSource(JSON.toJSONString(o));

            String documentId = elasticsearchService.save(indexQuery);

            log.info("success index, document id is:{}", documentId);
        });
    }

}
