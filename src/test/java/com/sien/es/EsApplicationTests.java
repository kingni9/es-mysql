package com.sien.es;

import com.sien.entity.Principal;
import com.sien.es.dao.PrincipalSearchRepository;
import com.sien.service.DataImportExecutor;
import com.sien.service.DataInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EsApplicationTests {
	@Autowired
	private DataImportExecutor dataImportExecutor;
	@Autowired
	private PrincipalSearchRepository principalSearchRepository;

	@Test
	public void contextLoads() {
		dataImportExecutor.dataImport("dataImport.xml");
	}

	@Test
	public void getTest() {
		Optional<Principal> optionalPrincipal = principalSearchRepository.findById(1);

		if(optionalPrincipal.isPresent()) {
			System.out.println(optionalPrincipal);
		}
	}

	@Test
	public void deleteTest() {
		principalSearchRepository.deleteById(1);
	}
}
