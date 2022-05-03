package com.mooc.meetingfilm.backendfilm.controller.vo;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseRequestVO;
import lombok.Data;

@Data
public class FilmSaveReqVO extends BaseRequestVO {

    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String mainImgAddress;
    private String filmScore;
    private String filmScorers;
    private String preSaleNum;
    private String boxOffice;
    private String filmTypeId;
    private String filmSourceId;
    private String filmCatIds;
    private String areaId;
    private String dateId;
    private String filmTime;
    private String directorId;
    private String actIds;
    private String roleNames;
    private String filmLength;
    private String biography;
    private String filmImgs;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
