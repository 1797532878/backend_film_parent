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

    /**
     * 演示线程池内容
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void threadPoolTest() throws ExecutionException, InterruptedException {
        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c3");
        CommandDemo c4 = new CommandDemo("c4");
        CommandDemo c5 = new CommandDemo("c5");

        Future<String>  q1 = c1.queue();
        Future<String>  q2 = c2.queue();
        Future<String>  q3 = c3.queue();
        Future<String>  q4 = c4.queue();
        Future<String>  q5 = c5.queue();

        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();
        String r5 = q5.get();

        System.out.println("r1 = " + r1 + ",r2 = " + r2 +",r3 = " + r3 +",r4 = " + r4 +",r5 = " + r5 );
    }

    /**
     * 演示信号量隔离
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void semTest() throws  InterruptedException {
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");
        MyThread t4 = new MyThread("t4");
        MyThread t5 = new MyThread("t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        Thread.sleep(2000l);
    }

    class MyThread extends Thread {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            CommandDemo c1 = new CommandDemo(name);
            System.out.println("CommandDemo,result = " +c1.execute());
        }
    }

    /**
     * 熔断演示
     */
    @Test
    public void CBTest() throws InterruptedException {
        // 正确-业务
        CommandDemo c1 = new CommandDemo("imooc");
        System.out.println(c1.execute());

        // 错误-业务
        CommandDemo c2= new CommandDemo("chenx-1");
        System.out.println(c2.execute());
        Thread.sleep(500L);
        // 正确-业务
        CommandDemo c3 = new CommandDemo("imooc2");
        System.out.println(c3.execute());

        // 半熔断状态
        Thread.sleep(5000L);
//            // 错误
//            CommandDemo c4= new CommandDemo("chenx-2");
//            System.out.println(c4.execute());

            // 正确-业务
            CommandDemo c5 = new CommandDemo("imooc5");
            System.out.println(c5.execute());

            // 正确-业务
            CommandDemo c6 = new CommandDemo("imooc6");
            System.out.println(c6.execute());
    }
}

