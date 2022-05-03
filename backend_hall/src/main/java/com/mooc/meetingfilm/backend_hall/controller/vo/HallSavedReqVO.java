package com.mooc.meetingfilm.backend_hall.controller.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseRequestVO;
import lombok.Data;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.hall.controller.vo
 * @description :
 **/
@Data
public class HallSavedReqVO extends BaseRequestVO {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
