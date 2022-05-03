package com.mooc.meetingfilm.backend_show_consumer.service.impl;

import com.mooc.meetingfilm.backend_show_consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ConsumerServiceAPIImpl implements ConsumerServiceAPI {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private LoadBalancerClient eurekaClient;

    @Override
    public String sayHello(String message) {
        // 准备工作
//        String hostName = "localhost";
//        int port = 8201;
//        String uri = "/provider/sayHello?message=" + message;

//        ServiceInstance choose = eurekaClient.choose("hello-service-provider");
//        String hostName = choose.getHost();
//        int port = choose.getPort();
        String uri = "/provider/sayHello?message=" + message;
        // http://localhost:8201/provider/sayHello?message=hello
        String url = "http://hello-service-provider" +uri;

        // invoker provider test

        return restTemplate.getForObject(url, String.class);
    }
}
