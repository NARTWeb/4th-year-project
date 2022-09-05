package com.nart;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.User;
import com.nart.service.UserService;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userTest {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Test
    public void Testlogin(){
        User liu = userService.findUser("liu", "123");
        System.out.println(liu);
    }

    @Test
    public void Testlogout(){
        boolean logout = userService.logout("1");
        System.out.println(logout);
    }

    @Test
    public void Testregister(){
        User register = userService.register("wewewe", "qi", "323423");
        System.out.println(register);
    }

    @Test
    public void TestshowUserInfo(){
        User user = userService.showUserInfo("1");
        System.out.println(user);
    }

    @Test
    public void TestshowUnameAvatar(){
        User user = userService.showUnameAvatar("1");
        System.out.println(user);
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
        IPage<User> u = userService.searchNew("u", pageVo);
        System.out.println(u.getRecords());
    }


}
