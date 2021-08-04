package com.shaw.kratos.service.impl.kafka;

import com.shaw.kratos.service.kafka.IKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shaw
 * @date 2021/6/24
 */
@Service
@Slf4j
public class KafkaService implements IKafkaService {

    @Resource(name = "kratosKafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendAsync(String topic, String data) {
        kafkaTemplate.send(topic, data);
    }

    @Override
    public boolean sendSync(String topic, String data) {
        try {
            SendResult<String, String> sendResult = kafkaTemplate.send(topic, data).get();
            return true;
        } catch (Exception e) {
            log.warn("发送kafka消息失败,errorInfo: " + e.getMessage());
            return false;
        }

    }
}
