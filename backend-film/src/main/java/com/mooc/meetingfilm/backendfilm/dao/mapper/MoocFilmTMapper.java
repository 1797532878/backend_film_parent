package com.mooc.meetingfilm.backendfilm.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.backendfilm.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2022-02-10
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    IPage<DescribeFilmsRespVO> describeFilms(Page<DescribeFilmsRespVO> page);


    DescribeFilmRespVO describeFilmById(String filmId);
}
