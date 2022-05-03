package com.mooc.meetingfilm.backendfilm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.backend_utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.FilmSaveReqVO;
import com.mooc.meetingfilm.backendfilm.service.FilmServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/films")
public class FilmController {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    // 获取演员列表
    @RequestMapping(value = "/actors",method = RequestMethod.GET)
    public BaseResponseVO describeActors(BasePageVO basePageVO) throws CommonServiceException {
        // 检查入参
        basePageVO.checkParam();

        //  调用逻辑层返回参数
        IPage<DescribeActorsRespVO> result = filmServiceAPI.describeActors(basePageVO.getNowPage(),basePageVO.getPageSize());

        return BaseResponseVO.success(describePageResult(result,"actors"));
    }

    // 获取影片列表
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeFilms(BasePageVO basePageVO) throws CommonServiceException {
        // 检查入参
        basePageVO.checkParam();

        //  调用逻辑层返回参数
        IPage<DescribeFilmsRespVO> films = filmServiceAPI.describeFilms(basePageVO.getNowPage(),basePageVO.getPageSize());

        return BaseResponseVO.success(describePageResult(films,"films"));
    }

    // 获取电影编号获取电影
    @RequestMapping(value = "/{filmId}",method = RequestMethod.GET)
    public BaseResponseVO describeFilmById(@PathVariable("filmId") String filmId) throws CommonServiceException {

        //  调用逻辑层返回参数
        DescribeFilmRespVO film = filmServiceAPI.describeFilmById(filmId);
        if (film == null) {
            return BaseResponseVO.success();
        } else {
            return BaseResponseVO.success(film);
        }

    }

    // 获取电影编号获取电影
    @RequestMapping(value = "/film:add",method = RequestMethod.POST)
    public BaseResponseVO saveFilmInfo(@RequestBody FilmSaveReqVO filmSaveReqVO) throws CommonServiceException {
        filmServiceAPI.saveFilm(filmSaveReqVO);
        return BaseResponseVO.success();
    }

    // 获取分页对象的公共接口
    private Map<String,Object> describePageResult(IPage page, String title) {
        Map<String,Object> result = Maps.newHashMap();

        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());

        result.put(title,page.getRecords());
        return result;
    }
}
