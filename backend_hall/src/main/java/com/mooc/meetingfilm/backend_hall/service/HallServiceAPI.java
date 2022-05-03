package com.mooc.meetingfilm.backend_hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.backend_hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.backend_hall.controller.vo.HallsReqVO;
import com.mooc.meetingfilm.backend_hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.hall.service
 * @description :
 **/
public interface HallServiceAPI {

    // 获取全部播放厅接口
    IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException;

    // 保存影厅信息
    void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException;

}
