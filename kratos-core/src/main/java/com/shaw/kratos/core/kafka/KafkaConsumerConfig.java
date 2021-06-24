package com.shaw.kratos.core.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxiao
 * @date 2021/6/24 4:27 下午
 */
@Configuration
//@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> consumerProperties() {
        Map<String, Object> props= new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,  "");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "");
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }

    @Bean("kratosKafkaListenerContainerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }


}
