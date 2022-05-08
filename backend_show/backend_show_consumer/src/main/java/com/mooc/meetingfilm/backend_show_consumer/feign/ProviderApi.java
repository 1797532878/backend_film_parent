package com.mooc.meetingfilm.backend_show_consumer.feign;

import com.mooc.meetingfilm.backend_show_consumer.feign.vo.UserModel;
import com.mooc.meetingfilm.feignconf.FeignHelloConf;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "hello-service-provider",path = "/provider",primary = true
//        ,fallback = ProviderFallBackAPIImpl.class
        ,fallbackFactory = FallBackFactory.class
//        url = "http://localhost:8201"
//        ,configuration = FeignHelloConf.class
)
public interface ProviderApi {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String invokeProviderController(@RequestParam("message") String message);

    @RequestMapping(value = "/{providerId}/sayHello",method = RequestMethod.POST)
    String invokeProviderPostTest(@RequestBody UserModel userModel, @PathVariable("providerId")String providerId, @RequestHeader("author")String author);

//    @RequestLine("GET /sayHelloFeignDefault?message={message}")
//    String invokerProviderControllerDefault(@Param("message")String message);
}
