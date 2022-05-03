package com.mooc.meetingfilm.backend_utils.common.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import lombok.Data;

/**
 * 表现层公共返回
 */
@Data
public class BaseResponseVO<M> {

    // 业务编号
    private Integer code;
    // 异常信息
    private String message;
    // 业务数据返回
    private M data;

    // 去掉构造方法 由底下的static方法提供
    private BaseResponseVO(){}

    // 成功但是无参数
    public static BaseResponseVO success () {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        return response;
    }

    // 成功有参数
    public static<M> BaseResponseVO success (M data) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    // 出现业务异常
    public static BaseResponseVO serviceException (CommonServiceException e) {
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }
}
