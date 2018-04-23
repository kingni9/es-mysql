package com.sien.service;

import com.alibaba.fastjson.JSON;
import com.sien.entity.DataInfo;
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
    private DataInfoService dataInfoService;

    public void dataImport(String fileName) {
        List<DataInfo> dataInfoList = dataInfoService.parseImportInfo(fileName);

        if(!CollectionUtils.isEmpty(dataInfoList)) {
            dataInfoList.stream().forEach(o -> {
                List<Map<String, Object>> maps = dataInfoService.getData(o);
                o.setRecords(maps);
                this.indexStore(o);
            });
        }

    }

    private void indexStore(DataInfo dataInfo) {
        dataInfo.getRecords().stream().forEach(o -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setIndexName(dataInfo.getIndex());
            indexQuery.setType(dataInfo.getType());
            indexQuery.setId(o.get(dataInfo.getPrimaryKey()).toString());
            indexQuery.setSource(JSON.toJSONString(o));

            String documentId = elasticsearchService.save(indexQuery);

            log.info("success index, document id is:{}", documentId);
        });
    }

}
