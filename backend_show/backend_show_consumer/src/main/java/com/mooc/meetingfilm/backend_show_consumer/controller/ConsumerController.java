package com.mooc.meetingfilm.backend_show_consumer.controller;

import com.mooc.meetingfilm.backend_show_consumer.feign.ProviderApi;
import com.mooc.meetingfilm.backend_show_consumer.feign.vo.UserModel;
import com.mooc.meetingfilm.backend_show_consumer.service.ConsumerServiceAPI;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI consumerServiceAPI;

    @Resource
    private ProviderApi providerApi;

//    @RequestMapping("/sayHelloFeignDefault")
//    public String sayHelloFeignDefault(String message) {
//        System.out.println("feign message = " + message);
//        return providerApi.invokerProviderControllerDefault(message);
//    }

    @RequestMapping("/sayHelloFeign")
    public String sayHelloFeign(String message) {
        System.out.println("feign message = " + message);
        return providerApi.invokeProviderController(message);
    }

    @RequestMapping("/sayHelloFeignPost")
    public String sayHelloFeignPost(String author, String providerId, UserModel userModel) {
        System.err.println("feign author = " + author);
        System.err.println("feign providerId = " + providerId);
        System.err.println("feign userModel = " + userModel);

        return providerApi.invokeProviderPostTest(userModel,providerId,author);
    }


    @RequestMapping("/sayHello")
    public String sayHello(String message) {
        return consumerServiceAPI.sayHello(message);
    }

}
