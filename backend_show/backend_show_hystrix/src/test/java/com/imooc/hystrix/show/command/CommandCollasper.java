package com.imooc.hystrix.show.command;

import com.netflix.hystrix.*;
import org.assertj.core.util.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CommandCollasper extends HystrixCollapser<List<String>,String,Integer> {

    private Integer id;

    public CommandCollasper(Integer id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("CommandCollasper"))
                    .andCollapserPropertiesDefaults(
                            HystrixCollapserProperties.defaultSetter()
                            .withTimerDelayInMilliseconds(1000)
                    ));
        this.id = id;
    }

    /**
     * 获取请求参数
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 批量业务处理
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collection) {
        return new BatchCommand(collection);
    }

    /**
     * 批量处理结果与请求业务之间映射关系处理
     * @param strings
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, Integer>> collection) {
        int count = 0;

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> response = iterator.next();
            String result = strings.get(count++);
            response.setResponse(result);
        }

    }
}

class BatchCommand extends HystrixCommand<List<String>> {

    private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection;

    protected BatchCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("bathCommand")));
        this.collection = collection;
    }

    @Override
    protected List<String> run() throws Exception {

        System.err.println("currentThread : " + Thread.currentThread().getName());
        List<String> result = Lists.newArrayList();

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> request = iterator.next();

            Integer reqParam = request.getArgument();

            // 具体业务逻辑
            result.add("Mooc req: " + reqParam);



        }

        return result;
    }
}
