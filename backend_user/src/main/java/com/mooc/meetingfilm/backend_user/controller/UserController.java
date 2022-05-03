package com.mooc.meetingfilm.backend_user.controller;

import com.mooc.meetingfilm.backend_user.controller.vo.LoginReqVO;
import com.mooc.meetingfilm.backend_user.service.UserServiceAPI;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.util.JwtTokenUtil;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceAPI serviceAPI;

    // post 请求  作为请求体进来的
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponseVO login (@RequestBody LoginReqVO reqVO) throws CommonServiceException {

        //数据验证
        reqVO.checkParam();
        // 验证用户名和密码是否正确
        String userId = serviceAPI.checkUserLogin(reqVO.getUsername(),reqVO.getPassword());
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(userId,randomKey);

        // randomKey token
        Map<String,String> result = new HashMap<>();
        result.put("randomKey",randomKey);
        result.put("token",token);


        return BaseResponseVO.success(result);
    }

}
