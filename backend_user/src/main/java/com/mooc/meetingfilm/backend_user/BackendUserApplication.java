package com.mooc.meetingfilm.backend_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// ComponentScan 自定义包扫描范围 扫描到其他组件
@SpringBootApplication
@ComponentScan(basePackages = {"com.mooc.meetingfilm"})
@MapperScan(basePackages = {"com.mooc.meetingfilm.backend_user.dao.mapper"})
public class BackendUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUserApplication.class, args);
    }

}
