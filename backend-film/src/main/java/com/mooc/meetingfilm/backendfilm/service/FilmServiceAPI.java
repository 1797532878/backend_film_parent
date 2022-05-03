package com.mooc.meetingfilm.backendfilm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.FilmSaveReqVO;

public interface FilmServiceAPI {

    // 获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize) throws CommonServiceException;

    // 获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(Integer nowPage, Integer pageSize);

    // 根据编号获取电影信息
    DescribeFilmRespVO describeFilmById(String filmId);

    // 保存电影信息
    void saveFilm(FilmSaveReqVO filmSaveReqVO) throws CommonServiceException;

}
