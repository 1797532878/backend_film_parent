package com.mooc.meetingfilm.backend_user.dao;

import com.mooc.meetingfilm.backend_user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.backend_user.dao.mapper.MoocBackendUserTMapper;
import com.mooc.meetingfilm.backend_utils.common.util.MD5Util;
import org.junit.Test;

import javax.annotation.Resource;

public class UserTest {

    @Resource
    private MoocBackendUserTMapper backendUserTMapper;

    @Test
    public void add () {
        MoocBackendUserT moocBackendUserT = new MoocBackendUserT();
        moocBackendUserT.setUserName("cc");
        moocBackendUserT.setUserPwd(MD5Util.encrypt("admin"));
        moocBackendUserT.setUserPhone("18588888888");

        backendUserTMapper.insert(moocBackendUserT);
    }

}
