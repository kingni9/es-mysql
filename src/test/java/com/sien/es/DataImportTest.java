package com.sien.es;

import com.alibaba.fastjson.JSON;
import com.sien.business.service.dataImport.DataImportExecutor;
import com.sien.entity.City;
import com.sien.es.dao.CitySearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhuangjt on 2018/5/3.
 */
@Slf4j
public class DataImportTest extends EsApplicationTests {
    @Autowired
    private DataImportExecutor dataImportExecutor;
    @Autowired
    private CitySearchRepository citySearchRepository;

    @Test
    public void contextLoads() {
        dataImportExecutor.dataImport("dataImport.xml");
    }

    @Test
    public void getTest() {
        Iterable<City> cityOptional = citySearchRepository.findAll();

        log.info("result:{}", JSON.toJSONString(cityOptional, true));
    }

    @Test
    public void deleteTest() {
        citySearchRepository.deleteAll();
    }
}
