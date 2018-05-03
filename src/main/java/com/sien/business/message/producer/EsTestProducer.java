package com.sien.business.message.producer;

import com.alibaba.fastjson.JSON;
import com.sien.constants.MessageTopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by zhuangjt on 2018/5/3.
 */
@Component
@Slf4j
public class EsTestProducer<K, V> {
    @Autowired
    private KafkaTemplate<K, V> kafkaTemplate;

    public void send(V value) {
        log.info("[{}]begin to send message:{}", MessageTopicConstant.ES_TEST_TOPIC, JSON.toJSON(value));

        kafkaTemplate.send(MessageTopicConstant.ES_TEST_TOPIC, value);

        log.info("[{}]end to send message", MessageTopicConstant.ES_TEST_TOPIC);
    }
}
