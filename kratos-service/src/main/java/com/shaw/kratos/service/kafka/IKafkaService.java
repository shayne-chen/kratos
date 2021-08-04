package com.shaw.kratos.service.kafka;

/**
 * @author shaw
 * @date 2021/6/24
 */
public interface IKafkaService {

    void sendAsync(String topic, String data);

    boolean sendSync(String topic, String data);
}
