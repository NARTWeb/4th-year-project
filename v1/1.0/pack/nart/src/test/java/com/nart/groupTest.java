package com.nart;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.pojo.*;
import com.nart.service.GroupService;
import com.nart.util.UserThreadLocal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class groupTest {
    @Test
    void contextLoads() {
    }
    @Autowired
    private GroupService groupService;

    @Test
    public void TestshowGroupMebList(){
        IPage<userGroup> page=new Page<>(1,3);
        List<user> users = groupService.showGroupMebList("1", page);
        System.out.println(users);
    }

    @Test
    public void TestchangeGroupInfo(){
        group group = new group();
        group.setId("1");
        group.setGroupName("llll");
        boolean b = groupService.changeGroupInfo(group);
        System.out.println(b);
    }

    @Test
    public void TestshowGroupList(){
        IPage<userGroup> page=new Page<>(1,3);
        List<group> groups = groupService.showGroupList(page);
        System.out.println(groups);
    }

    @Test
    public void TestleaveGroup(){
        boolean b = groupService.leaveGroup("1", "3");
        System.out.println(b);
    }

    @Test
    public void TestchangeGroupState(){
        boolean b = groupService.changeGroupState("1", "1", 1);
        System.out.println(b);
    }

    @Test
    public void showInviteList(){
        IPage<userGroup> page=new Page<>(1,3);
        List<groupInvite> groupInvites = groupService.showInviteList(page);
        System.out.println(groupInvites);

    }

    @Test
    public void sendInvite(){
        groupInvite groupInvite =new groupInvite();
        groupInvite.setId("2");
        groupInvite.setSenderId("1");
        groupInvite.setGroupId("1");
        groupInvite.setMsg("dwdw");
        groupInvite.setReceiverId("3");
        boolean b = groupService.sendInvite(groupInvite);
        System.out.println(b);

    }

    @Test
    public void respGroupInvite(){

        boolean b = groupService.respGroupInvite("2", true);
        System.out.println(b);

    }

    @Test
    public void createGroup(){
        boolean test = groupService.createGroup("test", "1");

        System.out.println(test);
    }

    @Test
    public void joinGroup(){
        user user = new user();
        user.setId("3");
        UserThreadLocal.put(user);
        boolean b = groupService.joinGroup("1565224110185975810");
        System.out.println(b);
    }
}
