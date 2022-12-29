package com.nart;


import com.nart.pojo.Comment;
import com.nart.pojo.User;
import com.nart.service.AdminService;
import com.nart.service.LoadDataInDataBase;
import com.nart.service.UserService;
import com.nart.util.FakeDataGenerator;
import net.minidev.json.JSONUtil;
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

    @Autowired
    private FakeDataGenerator fakeDataGenerator;

    @Autowired
    private LoadDataInDataBase loadDataInDataBase;

    @Autowired
    private AdminService adminService;

    @Test
    public void showAllStatusNum(){
        int i = adminService.showAllStatusNum();
        System.out.println(i);
    }

    @Test
    public void lock(){
        boolean b = adminService.blockUser("1574989632599367682");
        System.out.println(b);
    }
    @Test
    public void showAllCommentInfo(){
        List<Comment> comments = adminService.showAllCommentInfo();
        System.out.println(comments);
    }
    @Test
    public void showOnlineUserNum(){
        int i = adminService.showOnlineUserNum();
        System.out.println(i);
    }
    @Test
    public void showAllUserInfo(){
        List<User> users = adminService.showAllUserInfo();
        System.out.println(users);

    }

    @Test
    public void showAllUserNum(){
        int i = adminService.showAllUserNum();
        System.out.println(i);
    }

    @Test
    public void showOnlineUserInfo(){
        List<User> users = adminService.showOnlineUserInfo();
        System.out.println(users);
    }
//    @Test
//    public void setFakeDataGenerator(){
//        fakeDataGenerator.generateTestData(1);
//    }

    @Test
    public void updatetime(){
//        userService.upDatetime();
    }

//    @Test
//    public void setLoadDataInDataBase(){
//        List<User> Users = new ArrayList<>();
//        User user = new User();
//        user.setId("1");
//        user.setName("1");
//        user.setAvatar(null);
//        user.setPwd("1");
//        user.setEmail(null);
//        user.setAddress(null);
//        user.setAge(null);
//        user.setTel(null);
//        User user2 = new User();
//        user2.setId("2");
//        user2.setName("2");
//        user2.setAvatar(null);
//        user2.setPwd("2");
//        user2.setEmail(null);
//        user2.setAddress(null);
//        user2.setAge(null);
//        user2.setTel(null);
//        Users.add(user);
//        Users.add(user2);
//        System.out.println(Users);
//        boolean b = loadDataInDataBase.LoadListUser(Users);
//        System.out.println(b);
//    }



//    @Test
//    public void Testlogin(){
//        User liu = userService.findUser("liu", "123");
//        System.out.println(liu);
//    }
//
//    @Test
//    public void Testlogout(){
//        boolean logout = userService.logout("1");
//        System.out.println(logout);
//    }
//
//    @Test
//    public void Testregister(){
//        User register = userService.register("wewewe", "qi", "323423");
//        System.out.println(register);
//    }
//
//    @Test
//    public void TestshowUserInfo(){
//        User user = userService.showUserInfo("1");
//        System.out.println(user);
//    }
//
//    @Test
//    public void TestshowUnameAvatar(){
//        User user = userService.showUnameAvatar("1");
//        System.out.println(user);
//    }
//
//    @Test
//    public void TestchangeUserInfo(){
//        UserVo userVo = new UserVo();
//        userVo.setId("4");
//        userVo.setAddress("sfefef");
//        userVo.setAvatar("sqqq");
//        userVo.setBirthday("19980909");
//        userVo.setEmail("dede");
//        userVo.setPhone("3232323");
//        userVo.setPwd("1222");
//        userVo.setUname("qqqq");
//        boolean b = userService.changeUserInfo(userVo, "3");
//        System.out.println(b);
//    }
//
//    @Test
//    public void TestsearchNew(){
//        PageVo pageVo = new PageVo();
//        pageVo.setPageNum(1);
//        pageVo.setPageSize(1);
//        IPage<User> u = userService.searchNew("u", pageVo);
//        System.out.println(u.getRecords());
//    }


}
