package com.mooc.meetingfilm.backend_user.controller.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.util.ToolUtils;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseRequestVO;
import lombok.Data;

@Data
public class LoginReqVO extends BaseRequestVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        //TODO 验证数据合法性
        if (ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)) {
            throw new CommonServiceException(404,"username or password must be required!");
        }
    }
}
