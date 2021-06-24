package com.shaw.kratos.service.kafka;

/**
 * @author chenxiao
 * @date 2021/6/24 5:23 下午
 */
public interface IKafkaService {

    void sendAsync(String topic, String data);

    boolean sendSync(String topic, String data);
}
