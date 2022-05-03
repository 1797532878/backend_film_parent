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
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)
//                                .withCircuitBreakerForceOpen(true)// 强制开启
                                .withCircuitBreakerRequestVolumeThreshold(2) // 单位时间请求阈值
                                .withCircuitBreakerErrorThresholdPercentage(50) // 满足请求阈值时，超过50%则开启熔断
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

        if (name.startsWith("chenx")) {
            int i = 6/0;
        }

        String result = "CommandHelloWorld name : " + name;

        System.err.println(result+ ", currentThread-" + Thread.currentThread().getName());

        return result;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }

    @Override
    protected String getFallback() {

        String result = "Fallback name :" + name;

        System.err.println(result + ",currentThread-" + Thread.currentThread().getName());

        return result;
    }
}
