package com.nart.service;

import com.nart.dao.UserDao;
import com.nart.pojo.User;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Rollback
@Transactional
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Mock
    private HttpSession session;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;
    @Test
    void login() {
        Result apiu = loginService.login("apiu", "222222222", session);
        System.out.println(apiu);
        Object data = apiu.getData();
        Result a = new Result(true,200,"success",data);
        assertEquals(a,apiu);

        Result apiu1 = loginService.login("", "222222222", session);
        System.out.println(apiu1);
        Object data1 = apiu1.getData();
        Result a1 = new Result(false,10101,"username or password format incorrect",data1);
        assertEquals(a1,apiu1);

        Result apiu2 = loginService.login("swswsw", "222222222", session);
        assertEquals(apiu2,apiu2);
    }

    @Test
    void checkToken() {
        Result apiu = loginService.login("apiu", "222222222", session);
//        System.out.println(apiu);
        String data = String.valueOf(apiu.getData());
        User user = loginService.checkToken(data);
        User user1 = new User();
        user1 = user;
        assertEquals(user1,user);

        String data1 = "";
        User user2 = loginService.checkToken(data);
        User user3 = new User();
        user3 = user2;
        assertEquals(user3,user2);
    }

    @Test
    void logout() {
        User user = userDao.selectById("1574989632599367682");
        UserThreadLocal.put(user);
        Result apiu = loginService.login("apiu", "222222222", session);
        String data = String.valueOf(apiu.getData());
        Result logout = loginService.logout(data);
        System.out.println(logout);
        Result r = new Result(true,200,"success",null);
        assertEquals(r,logout);
    }

    @Test
    void register() {
        Result register = loginService.register("lll", "ttt", "weew", session);
//        System.out.println(register);
        String data = String.valueOf(register.getData());
        Result r = new Result(true,200,"success",data);
        assertEquals(r,register);

        Result register1 = loginService.register("", "ttt", "weew", session);
//        System.out.println(register);
        String data1 = String.valueOf(register1.getData());
        Result r1 = new Result(false,10101,"username or password format incorrect",data1);
        assertEquals(register1,register1);

        Result registera = loginService.register("apiu", "ttt", "weew", session);

        assertEquals(registera,registera);


    }
}