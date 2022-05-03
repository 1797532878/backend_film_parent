package com.mooc.meetingfilm.backend_hall.conf;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.hall.conf
 * @description :
 **/
@Configuration
public class RestConf {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
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
