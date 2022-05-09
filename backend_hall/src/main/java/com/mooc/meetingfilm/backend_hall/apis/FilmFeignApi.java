package com.mooc.meetingfilm.backend_hall.apis;


import com.mooc.meetingfilm.backendapi.film.FilmFeignApis;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.hall.apis
 * @description : film提供的接口服务
 **/
@FeignClient(name = "backend-film-service-provider")
public interface FilmFeignApi extends FilmFeignApis {

}
