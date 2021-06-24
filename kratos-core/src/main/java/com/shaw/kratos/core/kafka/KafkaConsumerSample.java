package com.shaw.kratos.core.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author chenxiao
 * @date 2021/6/24 4:45 下午
 */
public class KafkaConsumerSample {

    @KafkaListener(groupId = "", topics = {"", ""}, containerFactory = "kratosKafkaListenerContainerFactory")
    public void doCustomizeConsumer(ConsumerRecord<String, String> data) {
        System.out.println("消息内容 = " + data.value());

    }

    @KafkaListener(groupId = "", topics = {"", ""})
    public void doConsumer(ConsumerRecord<String, String> data) {
        System.out.println("消息内容 = " + data.value());

    }
}
