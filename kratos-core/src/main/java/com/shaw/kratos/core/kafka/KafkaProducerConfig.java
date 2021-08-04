package com.shaw.kratos.core.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaw
 * @date 2021/6/24
 */
@Configuration
//@EnableKafka
public class KafkaProducerConfig {

    @Bean
    public Map<String, Object> producerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "");
        props.put(ProducerConfig.RETRIES_CONFIG, "");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, "");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "");
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "");
        props.put(ProducerConfig.ACKS_CONFIG, "");
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    @Bean("kratosKafkaTemplate")
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory(), true);
    }

}
