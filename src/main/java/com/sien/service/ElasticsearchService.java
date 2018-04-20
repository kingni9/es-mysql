package com.sien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Component
public class ElasticsearchService {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public String save(IndexQuery indexQuery) {
        String documentId = elasticsearchOperations.index(indexQuery);
        elasticsearchOperations.refresh(indexQuery.getIndexName());

        return documentId;
    }
}
