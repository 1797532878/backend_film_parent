package com.mooc.meetingfilm.backend_show_consumer.controller;

import com.mooc.meetingfilm.backend_show_consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI consumerServiceAPI;

    @RequestMapping("/sayHello")
    public String sayHello(String message) {
        return consumerServiceAPI.sayHello(message);
    }

}
