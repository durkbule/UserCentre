package com.lyt.usercentre.webapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.lyt.usercentre.webapi.Feign")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.lyt.usercentre.webapi.dao")
public class UsercentreWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercentreWebapiApplication.class, args);
    }

}
