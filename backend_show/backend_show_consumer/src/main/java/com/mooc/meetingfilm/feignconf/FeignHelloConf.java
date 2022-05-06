package com.mooc.meetingfilm.feignconf;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignHelloConf {

    @Bean
    public Contract contract () {
        return new Contract.Default();
    }

}
