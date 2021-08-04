package com.shaw.kratos.core.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author shaw
 * @date 2021/6/24
 */
public class KafkaConsumerSample {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerSample.class);

    @KafkaListener(groupId = "", topics = {"", ""}, containerFactory = "kratosKafkaListenerContainerFactory")
    public void doCustomizeConsumer(ConsumerRecord<String, String> data) {
        logger.info("消息内容 = " + data.value());

    }

    @KafkaListener(groupId = "", topics = {"", ""})
    public void doConsumer(ConsumerRecord<String, String> data) {
        logger.info("消息内容 = " + data.value());

    }
}
