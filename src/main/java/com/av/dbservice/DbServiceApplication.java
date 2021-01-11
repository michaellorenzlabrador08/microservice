package com.av.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
public class DbServiceApplication {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(DbServiceApplication.class, args);
    }

}
