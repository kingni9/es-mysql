package com.sien.es.dao;

import com.sien.entity.Principal;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by zhuangjt on 2018/4/19.
 */
public interface PrincipalSearchRepository extends ElasticsearchRepository<Principal, Integer> {

}
