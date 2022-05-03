package com.mooc.meetingfilm.backend_show_consumer.ribbon.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

public class MyRule extends AbstractLoadBalancerRule {

    public MyRule(){

    }

    @Override
    public Server choose(Object o) {
        // 无论传入什么 都返回null
        return null;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
