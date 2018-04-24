package com.sien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Component
public class ElasticsearchService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public String save(IndexQuery indexQuery) {
        String documentId = elasticsearchTemplate.index(indexQuery);

        return documentId;
    }
}
