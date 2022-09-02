package com.nart;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.pojo.friendChat;
import com.nart.pojo.groupChat;
import com.nart.pojo.userGroup;
import com.nart.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class chatTest {
    @Test
    void contextLoads() {
    }
    @Autowired
    private ChatService chatService;


    @Test
    public void TestsendFriendMsg(){
        friendChat friendChat = new friendChat();
        friendChat.setSenderId("1");
        friendChat.setReceiverId("2");
        friendChat.setType("wenzi");
        friendChat.setMsg("dwdwde");
        friendChat.setDate(123132323L);
        boolean b = chatService.sendFriendMsg(friendChat);
        System.out.println(b);
    }

    @Test
    public void TestsendGroupMsg(){
        groupChat groupChat = new groupChat();
        groupChat.setGroupId("1");
        groupChat.setSenderId("1");
        groupChat.setDate(112323L);

        groupChat.setMsg("sfefef");
        groupChat.setType("tupian");
        boolean b = chatService.sendGroupMsg(groupChat);
        System.out.println(b);

    }

    @Test
    public void TestrecivicefriendMsg(){
        IPage<userGroup> page=new Page<>(1,3);
        List<friendChat> friendChats = chatService.recivicefriendMsg("2",page);
        System.out.println(friendChats);
    }

    @Test
    public void TestrecivicegroupMsg(){
        IPage<userGroup> page=new Page<>(1,3);
        List<groupChat> groupChats = chatService.recivicegroupMsg("1",page);
        System.out.println(groupChats);

    }

    @Test
    public void TestshowFriendHistory(){
        IPage<userGroup> page=new Page<>(1,3);
        List<friendChat> friendChats = chatService.showFriendHistory("1",page);
        System.out.println(friendChats);
    }

}
