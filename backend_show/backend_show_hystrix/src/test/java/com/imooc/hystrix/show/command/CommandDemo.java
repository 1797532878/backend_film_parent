package com.imooc.hystrix.show.command;

import com.netflix.hystrix.*;

public class CommandDemo extends HystrixCommand<String> {

    private String name;

    protected CommandDemo(String name) {

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                                .withRequestCacheEnabled(false)
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)// 切换线程池隔离为信号量隔离
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)
//                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
//                ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MyThreadPool"))
//                // 线程池参数
//                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
//                    .withCoreSize(2)
//                    .withMaximumSize(3).withAllowMaximumSizeToDivergeFromCoreSize(true)
//                    .withMaxQueueSize(2)
                        ));
        // 请求缓存开关

        this.name = name;

    }

    // 单词请求调用的业务方法
    @Override
    protected String run() throws Exception {

//        Thread.sleep(800l);

        String result = "CommandHelloWorld name : " + name;

        System.err.println(result+ ", currentThread-" + Thread.currentThread().getName());

        return result;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
