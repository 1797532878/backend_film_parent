package com.mooc.meetingfilm.backend_show_consumer.feign;

import com.mooc.meetingfilm.backend_show_consumer.feign.vo.UserModel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Primary
public class ProviderApiImpl implements ProviderApi{
    @Override
    public String invokeProviderController(String message) {
        return null;
    }

    @Override
    public String invokeProviderPostTest(UserModel userModel, String providerId, String author) {
        return null;
    }

//    @Override
//    public String invokerProviderControllerDefault(String message) {
//        return null;
//    }

}
