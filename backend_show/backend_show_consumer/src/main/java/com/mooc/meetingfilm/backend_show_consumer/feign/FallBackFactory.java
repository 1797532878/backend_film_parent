package com.mooc.meetingfilm.backend_show_consumer.feign;

import com.mooc.meetingfilm.backend_show_consumer.feign.vo.UserModel;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

@Service
public class FallBackFactory implements FallbackFactory {
    @Override
    public ProviderApi create(Throwable throwable) {
        return new ProviderApi() {
            @Override
            public String invokeProviderController(String message) {
                return "invokeProviderController FallBackFactory message = " + message;
            }

            @Override
            public String invokeProviderPostTest(UserModel userModel, String providerId, String author) {
                return "invokeProviderPostTest FallBackFactory message = " + userModel;
            }
        };
    }
}
