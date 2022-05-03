package com.mooc.meetingfilm.backend_user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.backend_user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.backend_user.dao.mapper.MoocBackendUserTMapper;
import com.mooc.meetingfilm.backend_utils.common.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BackendUserApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUserTMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void add () {
        MoocBackendUserT moocBackendUserT = new MoocBackendUserT();
        moocBackendUserT.setUserName("cc");
        moocBackendUserT.setUserPwd(MD5Util.encrypt("admin"));
        moocBackendUserT.setUserPhone("18588888888");

        backendUserTMapper.insert(moocBackendUserT);
    }

//    @Test
//    public void select(){
//        Page<MoocBackendUserT> page = new Page<>(1,2);
//        QueryWrapper<MoocBackendUserT> queryWrapper = new QueryWrapper<>();
//        IPage<MoocBackendUserT> moocBackendUserTIPage = backendUserTMapper.selectPage(page, queryWrapper);
//        System.out.println(moocBackendUserTIPage.getRecords());
//    }
}
