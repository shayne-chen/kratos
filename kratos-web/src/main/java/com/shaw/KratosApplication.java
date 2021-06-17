package com.shaw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@MapperScan("com.shaw.kratos.dao.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(locations = {"classpath:spring-all.xml"})
public class KratosApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(KratosApplication.class, args);
        System.out.println(String.format("------- Kratos Application Started ----, 耗时 %s ms", (System.currentTimeMillis() - startTime)));
    }
}
