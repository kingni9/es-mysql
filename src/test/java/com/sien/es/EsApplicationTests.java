package com.sien.es;

import com.alibaba.fastjson.JSON;
import com.sien.entity.City;
import com.sien.es.dao.CitySearchRepository;
import com.sien.business.service.dataImport.DataImportExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EsApplicationTests {
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

		System.out.println(JSON.toJSONString(cityOptional, true));
	}

	@Test
	public void deleteTest() {
		citySearchRepository.deleteAll();
	}
}
