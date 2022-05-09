package com.mooc.meetingfilm.backendfilm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.backend_utils.common.exception.CommonServiceException;
import com.mooc.meetingfilm.backend_utils.common.util.ToolUtils;
import com.mooc.meetingfilm.backendapi.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.backendfilm.controller.vo.FilmSaveReqVO;
import com.mooc.meetingfilm.backendfilm.dao.entity.MoocFilmActorT;
import com.mooc.meetingfilm.backendfilm.dao.entity.MoocFilmInfoT;
import com.mooc.meetingfilm.backendfilm.dao.entity.MoocFilmT;
import com.mooc.meetingfilm.backendfilm.dao.mapper.MoocActorTMapper;
import com.mooc.meetingfilm.backendfilm.dao.mapper.MoocFilmActorTMapper;
import com.mooc.meetingfilm.backendfilm.dao.mapper.MoocFilmInfoTMapper;
import com.mooc.meetingfilm.backendfilm.dao.mapper.MoocFilmTMapper;
import com.mooc.meetingfilm.backendfilm.service.FilmServiceAPI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FilmServiceImpl implements FilmServiceAPI {

    @Resource
    private MoocActorTMapper moocActorTMapper;

    @Resource
    private MoocFilmTMapper filmTMapper;

    @Resource
    private MoocFilmInfoTMapper moocFilmInfoTMapper;

    @Resource
    private MoocFilmActorTMapper filmActorTMapper;

    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException {
        // 查询演员列表
        return moocActorTMapper.describeActors(new Page<>(nowPage,pageSize));
    }

    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(Integer nowPage, Integer pageSize) {
        // 影片列表查询
        return filmTMapper.describeFilms(new Page<>(nowPage,pageSize));
    }

    // 根据主键获取电影详情
    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) {
        return filmTMapper.describeFilmById(filmId);
    }

    @Override
    public void saveFilm(FilmSaveReqVO reqVO) throws CommonServiceException {

        try{
            // 保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));

            filmTMapper.insert(film);
            // 保存电影子表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();

            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());


            moocFilmInfoTMapper.insert(filmInfo);

            String[] actorId = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if(actorId.length != roleNames.length){
                throw new CommonServiceException(500, "演员和角色名数量不匹配");
            }

            for(int i=0;i<actorId.length;i++){
                // 保存演员映射表
                MoocFilmActorT filmActor = new MoocFilmActorT();

                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);


                filmActorTMapper.insert(filmActor);
            }
        }catch (Exception e){
            throw new CommonServiceException(500, e.getMessage());
        }


    }
}
