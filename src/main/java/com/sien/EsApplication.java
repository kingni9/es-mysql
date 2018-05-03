package com.sien;

import com.sien.business.message.producer.EsTestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class EsApplication {

	@Autowired
	private EsTestProducer<String, String> esTestProducer;

	@Scheduled(cron = "*/5 * * * * ?")
	public void execute() {
		esTestProducer.send("Testing ... " + System.currentTimeMillis());
	}

	public static void main(String[] args) {
		SpringApplication.run(EsApplication.class, args);
	}
}
