package com.mooc.meetingfilm.backend_show_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// DiscoveryClient可以集成大多数注册中心 @EnableEurekaClient只能支持eureka
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class BackendShowConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendShowConsumerApplication.class, args);
    }

}
