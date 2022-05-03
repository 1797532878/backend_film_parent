package com.mooc.meetingfilm.backendfilm.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.backendfilm.dao.entity.MoocActorT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2022-02-10
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {

    IPage<DescribeActorsRespVO> describeActors(Page<DescribeActorsRespVO> page);

}
