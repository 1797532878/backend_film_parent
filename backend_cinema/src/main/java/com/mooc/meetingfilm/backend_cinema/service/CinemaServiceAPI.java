package com.mooc.meetingfilm.backend_cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.backend_cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.backend_cinema.controller.vo.DescribeCinemasRespVO;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.cinema.service
 * @description :
 **/
public interface CinemaServiceAPI {

    // 保存影院
    void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException;

    // 获取影院列表
    IPage<DescribeCinemasRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException;

}
