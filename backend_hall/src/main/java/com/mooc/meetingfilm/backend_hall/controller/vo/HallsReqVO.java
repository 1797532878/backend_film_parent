package com.mooc.meetingfilm.backend_hall.controller.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.vo.BasePageVO;
import lombok.Data;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.hall.controller.vo
 * @description :
 **/
@Data
public class HallsReqVO extends BasePageVO {

    private String cinemaId;

    @Override
    public void checkParam() throws CommonServiceException {
        super.checkParam();
    }
}
