package com.imooc.hystrix.show.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CommandTest {

    @Test
    public void executeTest(){

        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("execute");

        long endTime = System.currentTimeMillis();

        // 同步执行command
        String result = commandDemo.execute();
        System.out.println("result=" + result + ",speeding=" + (endTime - beginTime));

    }

    @Test
    public void queueTest() throws ExecutionException, InterruptedException {
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("queue");

        Future<String>  queue = commandDemo.queue();

        long endTime = System.currentTimeMillis();

        System.out.println(" Future end " + ",speeding=" + (endTime - beginTime));

        long endTime2 = System.currentTimeMillis();

        System.out.println("result=" + queue.get() + ",speeding=" + (endTime2 - beginTime));
    }

    @Test
    public void observeTest(){
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("observe");

        // 阻塞式调用
        Observable<String> observe = commandDemo.observe();

        String result = observe.toBlocking().single();

        long endTime2 = System.currentTimeMillis();

        System.out.println("result=" + result + ",speeding=" + (endTime2 - beginTime));

        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("observe,onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("observe,onError-throwable=" + throwable);

            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("observe,onNext,spending = " + (endTime - beginTime));
            }
        });

    }

    @Test
    public void toObserveTest(){
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("toObserve");

        // 阻塞式调用
        Observable<String> toObserve = commandDemo.observe();

        String result = toObserve.toBlocking().single();

        long endTime2 = System.currentTimeMillis();

        System.out.println("result=" + result + ",speeding=" + (endTime2 - beginTime));

        // 非阻塞式调用
        toObserve.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("toObserve,onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("toObserve,onError-throwable=" + throwable);

            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("toObserve,onNext,spending = " + (endTime - beginTime));
            }
        });

    }

    /**
     * 演示请求缓存
     */
    @Test
    public void requestCacheTest(){

        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();

        long beginTime = System.currentTimeMillis();

        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c1");

        // 同步执行command

        //第一次 请求
        String result1 = c1.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("result1=" + result1 + ",speeding=" + (endTime - beginTime));
        //第二次 请求
        String result2 = c2.execute();
        endTime = System.currentTimeMillis();
        System.out.println("result2=" + result2 + ",speeding=" + (endTime - beginTime));
        //第三次 请求
        String result3 = c3.execute();
        endTime = System.currentTimeMillis();
        System.out.println("result=3" + result3 + ",speeding=" + (endTime - beginTime));

        requestContext.close();
    }


}

