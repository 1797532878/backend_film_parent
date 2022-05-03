package com.mooc.meetingfilm.backend_user.service;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;

public interface UserServiceAPI {

    String checkUserLogin (String username, String password) throws CommonServiceException;

}
