package com.shaw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@MapperScan("com.shaw.kratos.dao.mapper")
@SpringBootApplication
public class KratosApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(KratosApplication.class, args);
        System.out.println(String.format("-----started----, 耗时 %s ms", (System.currentTimeMillis() - startTime)));
    }
}
