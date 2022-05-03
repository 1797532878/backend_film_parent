package com.imooc.hystrix.show.command;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

public class ObserveCommandTest {

    @Test
    public void observeTest() throws InterruptedException {
        long beginTime = System.currentTimeMillis();

        ObserveCommandDemo observeCommandDemo = new ObserveCommandDemo("observeCommandDemo");

        // 阻塞式调用
        Observable<String> observe = observeCommandDemo.observe();

//        String result = observe.toBlocking().single();
//
//        long endTime2 = System.currentTimeMillis();
//
//        System.out.println("result=" + result + ",speeding=" + (endTime2 - beginTime));

        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("observeCommandDemo,onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("observeCommandDemo,onError-throwable=" + throwable);

            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("observeCommandDemo,onNext,spending = " + (endTime - beginTime));
            }
        });
        Thread.sleep(2000l);
    }

}
