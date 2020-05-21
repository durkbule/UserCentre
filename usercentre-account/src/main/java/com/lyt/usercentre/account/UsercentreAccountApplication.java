package com.lyt.usercentre.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.lyt.usercentre.account.feign")
@EnableDiscoveryClient
@MapperScan("com.lyt.usercentre.account.dao")
@SpringBootApplication
public class UsercentreAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercentreAccountApplication.class, args);
    }

}
