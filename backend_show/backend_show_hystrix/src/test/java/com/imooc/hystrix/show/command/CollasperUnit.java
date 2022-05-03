package com.imooc.hystrix.show.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CollasperUnit {

    @Test
    public void collasperTest() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        // 构建请求 --> 主要优化点，多个服务调用的多次Http请求合并 降低握手时间
        // 缺点：很少有机会对同一个服务进行多次HTTP调用，同时还要足够的"近"
        CommandCollasper c1 = new CommandCollasper(1);
        CommandCollasper c2 = new CommandCollasper(2);
        CommandCollasper c3 = new CommandCollasper(3);
        CommandCollasper c4 = new CommandCollasper(4);

        // 获取 结果 足够的近 -->  10ms
        Future<String> q1 = c1.queue();
        Thread.sleep(1000L);
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Thread.sleep(1100L);
        Future<String> q4 = c4.queue();

        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();

        System.err.println(r1 + "," + r2 + "," + r3 + "," + r4);

        context.close();
    }

}
