package com.mooc.meetingfilm.backend_cinema.controller.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseRequestVO;

import lombok.Data;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.cinema.controller.vo
 * @description :
 **/
@Data
public class CinemaSavedReqVO extends BaseRequestVO {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
