package com.nart;

import com.nart.pojo.User;
import com.nart.service.LoadDataInDataBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LoadDataInDataBaseTest {
    @Test
    void contextLoads() {
    }

    @Autowired
    private LoadDataInDataBase loadDataInDataBase;

    @Test
    public void LoadListUserTEst(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setName("lol1");
        user1.setPwd("1111111");

        user2.setName("lol2");
        user2.setPwd("222222");

        user3.setName("lol3");
        user3.setPwd("333333");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        loadDataInDataBase.LoadListUser(users);

    }
}
