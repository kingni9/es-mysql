package com.sien.business.message.consumer;

import com.sien.constants.MessageTopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhuangjt on 2018/5/3.
 */
@Component
@Slf4j
public class EsTestConsumer {
    @KafkaListener(topics = MessageTopicConstant.ES_TEST_TOPIC)
    public void consumer(ConsumerRecord<?, ?> record) {
        log.info("Consumer Message .. " + record.topic() + "--" + record.key() + "--" +  record.value());
    }
}
