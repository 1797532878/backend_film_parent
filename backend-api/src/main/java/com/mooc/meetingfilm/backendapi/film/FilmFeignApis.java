package com.mooc.meetingfilm.backendapi.film;

import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.backendapi.film.vo.DescribeFilmRespVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Film提供的公共服务类接口
 */
public interface FilmFeignApis {

    /**
     * 获取电影编号获取电影  对外暴露的接口服务
     * @param filmId
     * @return BaseResponseVO
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/films/{filmId}",method = RequestMethod.GET)
    BaseResponseVO<DescribeFilmRespVO> describeFilmById(@PathVariable("filmId") String filmId) throws CommonServiceException;

}
