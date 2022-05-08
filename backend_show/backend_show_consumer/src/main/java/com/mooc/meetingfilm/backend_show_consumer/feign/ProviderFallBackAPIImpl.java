package com.mooc.meetingfilm.backend_show_consumer.feign;

import com.mooc.meetingfilm.backend_show_consumer.feign.vo.UserModel;
import org.springframework.stereotype.Service;

/**
 * 降级实现
 */
@Service
public class ProviderFallBackAPIImpl implements ProviderApi{
    @Override
    public String invokeProviderController(String message) {
        return "invokeProviderController fallback message = " + message;
    }

    @Override
    public String invokeProviderPostTest(UserModel userModel, String providerId, String author) {
        return "invokeProviderPostTest fallback message = " + userModel;
    }
}
