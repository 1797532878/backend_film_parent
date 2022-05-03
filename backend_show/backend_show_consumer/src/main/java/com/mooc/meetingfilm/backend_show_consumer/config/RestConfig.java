package com.mooc.meetingfilm.backend_show_consumer.config;

import com.mooc.meetingfilm.backend_show_consumer.ribbon.rules.MyRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡规则
     * @return
     */
    @Bean
    public IRule iRule(){
        return new RandomRule();
//        return new MyRule();
    }

    @Bean
    public IPing iPing(){
//        return new PingUrl(false,"/abc");
        return new NIWSDiscoveryPing();
    }

}
