package com.nart;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.pojo.friend;
import com.nart.pojo.friendReq;
import com.nart.pojo.user;
import com.nart.service.FriendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class friendTest {
    @Test
    void contextLoads() {
    }
    @Autowired
    private FriendService friendService;

    @Test
    public void TestshowFriendList(){
        IPage<friend> page=new Page<>(1,2);
        List<friend> friends = friendService.showFriendList(page, "1");
        System.out.println(friends);
    }

    @Test
    public void TestsearchFriend(){
        IPage<friend> page=new Page<>(1,2);
        List<user> liu = friendService.searchFriend("liu", page);
        System.out.println(liu);
    }

    @Test
    public void TestdelFriend(){
        boolean b = friendService.delFriend("2", "1");
        System.out.println(b);
    }

    @Test
    public void TestchangeFriendState(){
        boolean b = friendService.changeFriendState("2", "1", 1);
        System.out.println(b);
    }

    @Test
    public void TestshowReqList(){
        IPage<friend> page = new Page<>(1,2);
        List<friendReq> friendReqs = friendService.showReqList(page, "1");
        System.out.println(friendReqs);
    }

    @Test
    public void TestsendFriendReq(){
        boolean qqqqq = friendService.sendFriendReq("2", "1", "qqqqq");
        System.out.println(qqqqq);
    }

    @Test
    public void TestrespFriendReq(){
        boolean b = friendService.respFriendReq("1", true);
        System.out.println(b);
    }

}
