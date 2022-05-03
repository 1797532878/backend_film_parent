package com.mooc.meetingfilm.backend_utils.common.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;

/**
 * 公共的 请求对象
 */
public abstract class BaseRequestVO {

    /**
     * 公共的参数验证方法
     */
    public abstract void checkParam() throws CommonServiceException;

}
