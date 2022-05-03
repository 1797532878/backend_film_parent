package com.mooc.meetingfilm.backend_utils.common.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import lombok.Data;

@Data
public class BasePageVO extends BaseRequestVO{

    private Integer nowPage = 1;
    private Integer pageSize = 10;


    @Override
    public void checkParam() throws CommonServiceException {
        // TODO nowPage and pageSize 不能为空 balabala
    }
}
