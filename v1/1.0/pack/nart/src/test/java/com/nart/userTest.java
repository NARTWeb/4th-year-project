package com.nart;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.user;
import com.nart.service.UserService;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class userTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Test
    public void Testlogin(){
        boolean liu = userService.login("liu", "123");
        System.out.println(liu);
    }

    @Test
    public void Testlogout(){
        boolean logout = userService.logout("1");
        System.out.println(logout);
    }

    @Test
    public void Testregister(){
        boolean register = userService.register("wewewe", "qi", "323423");
        System.out.println(register);
    }

    @Test
    public void TestshowUserInfo(){
        user user = userService.showUserInfo("1");
        System.out.println(user);
    }

    @Test
    public void TestshowUnameAvatar(){
        List<String> strings = userService.showUnameAvatar("1");
        System.out.println(strings);
    }

    @Test
    public void TestchangeUserInfo(){
        UserVo userVo = new UserVo();
        userVo.setId("4");
        userVo.setAddress("sfefef");
        userVo.setAvatar("sqqq");
        userVo.setBirthday("19980909");
        userVo.setEmail("dede");
        userVo.setPhone("3232323");
        userVo.setPwd("1222");
        userVo.setUname("qqqq");
        boolean b = userService.changeUserInfo(userVo, "3");
        System.out.println(b);
    }

    @Test
    public void TestsearchNew(){
        PageVo pageVo = new PageVo();
        pageVo.setPageNum(1);
        pageVo.setPageSize(1);
        IPage<user> u = userService.searchNew("u", pageVo);
        System.out.println(u.getRecords());
    }


}
