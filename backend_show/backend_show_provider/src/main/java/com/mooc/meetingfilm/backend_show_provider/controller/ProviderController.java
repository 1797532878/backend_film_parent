package com.mooc.meetingfilm.backend_show_provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/sayHello",method = RequestMethod.GET)
    public String providerSayHello (String message) {
        log.info("provider sayHello port:{},message:{}",port,message);

        return "Provider sayHello port:" + port + "x, message:" + message;
    }

    @RequestMapping(value = "/provider/sayHelloFeignDefault",method = RequestMethod.GET)
    public String providerSayHelloDefault (String message) {
        log.info("provider sayHelloFeignDefault port:{},message:{}",port,message);

        return "Provider sayHelloFeignDefault port:" + port + "x, message:" + message;
    }


    @RequestMapping(value = "/provider/{providerId}/sayHello",method = RequestMethod.POST)
    public String postTest (@RequestBody String json, @PathVariable("providerId")String providerId,@RequestHeader("author")String author) {
        log.error("provider sayHello port:{},message:{},providerId:{},author:{}",port,json,providerId,author);

        return "Provider sayHello port:" + port + "x, message:" + json;
    }
}
