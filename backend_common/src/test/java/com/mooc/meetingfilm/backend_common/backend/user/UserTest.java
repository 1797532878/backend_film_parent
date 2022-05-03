//package com.mooc.meetingfilm.backend_common.backend.user;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.mooc.meetingfilm.backend_common.BackendCommonApplicationTests;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//
//public class UserTest extends BackendCommonApplicationTests {
//
//    @Resource
//    private MoocBackendUserTMapper backendUser;
//
//    @Test
//    public void add(){
//        MoocBackendUserT user = new MoocBackendUserT();
//        user.setUserName("admin");
//        user.setUserPwd("admin");
//        user.setUserPhone("13912341234");
//        backendUser.insert(user);
//    }
//
//    @Test
//    public void select(){
//        Page<MoocBackendUserT> page = new Page<>(1,2);
//        QueryWrapper<MoocBackendUserT> queryWrapper = new QueryWrapper<>();
//        IPage<MoocBackendUserT> moocBackendUserTIPage = backendUser.selectPage(page, queryWrapper);
//        System.out.println(moocBackendUserTIPage);
//    }
//
//    @Test
//    public void select1(){
//        MoocBackendUserT admin = backendUser.describeUserByUserName("admin");
//        System.out.println("user=" + admin);
//    }
//
//
//    @Test
//    public void update(){
//        MoocBackendUserT user = new MoocBackendUserT();
//        user.setUuid(2);
//        user.setUserName("");
//        user.setUserPwd("");
//        user.setUserPhone("");
//
//    }
//
//    @Test
//    public void delete(){
//
//    }
//}
