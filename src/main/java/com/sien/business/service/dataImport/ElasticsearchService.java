package com.sien.business.service.dataImport;

import com.alibaba.fastjson.JSON;
import com.sien.entity.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Component
public class ElasticsearchService {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void save(DataInfo dataInfo) {
        List<IndexQuery> indexQueries = dataInfo.getRecords().stream().map(o -> new IndexQueryBuilder()
                .withIndexName(dataInfo.getIndex())
                .withType(dataInfo.getType())
                .withId(o.get(dataInfo.getPrimaryKey()).toString())
                .withSource(JSON.toJSONString(o))
                .build())
                .collect(Collectors.toList());

        elasticsearchOperations.bulkIndex(indexQueries);
        elasticsearchOperations.refresh(dataInfo.getIndex());
    }
}
