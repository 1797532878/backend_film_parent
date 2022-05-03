package com.mooc.meetingfilm.backend_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mooc.meetingfilm.backend_user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.backend_user.dao.mapper.MoocBackendUserTMapper;
import com.mooc.meetingfilm.backend_user.service.UserServiceAPI;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceAPI {

    @Resource
    private MoocBackendUserTMapper userMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {
        // 根据用户名获取用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",username);
        MoocBackendUserT user = null;
        List<MoocBackendUserT> list =  userMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            user = list.stream().findFirst().get();
        } else {
            throw new CommonServiceException(404,"用户名输入有误！");
        }

        // 验证密码是否正确
        String encrypt = MD5Util.encrypt(password);

        if (!encrypt.equals(user.getUserPwd())) {
            throw new CommonServiceException(500,"用户密码输入有误");
        } else {
            return user.getUuid() + "";
        }
    }
}
