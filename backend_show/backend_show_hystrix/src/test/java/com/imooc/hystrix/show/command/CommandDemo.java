package com.imooc.hystrix.show.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandDemo extends HystrixCommand<String> {

    private String name;

    protected CommandDemo(String name) {

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                                .withRequestCacheEnabled(false)));
        // 请求缓存开关

        this.name = name;

    }

    // 单词请求调用的业务方法
    @Override
    protected String run() throws Exception {

        Thread.sleep(800l);

        String result = "CommandHelloWorld name : " + name;

        System.err.println(result+ ", currentThread-" + Thread.currentThread().getName());

        return result;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
