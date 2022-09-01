package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.groupDao;
import com.nart.dao.groupInviteDao;
import com.nart.dao.userDao;
import com.nart.dao.userGroupDao;
import com.nart.pojo.group;
import com.nart.pojo.groupInvite;
import com.nart.pojo.user;
import com.nart.pojo.userGroup;
import com.nart.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private userGroupDao userGroupDao;
    @Autowired
    private userDao userDao;
    @Autowired
    private groupDao groupDao;

    @Autowired
    private groupInviteDao groupInviteDao;

    @Override
    public List<user> showGroupMebList(String gid, IPage page) {
        LambdaQueryWrapper<userGroup> lqw = new LambdaQueryWrapper<userGroup>();
        lqw.eq(userGroup::getGid,gid);

        IPage iPage = userGroupDao.selectPage(page, lqw);
        List<userGroup> records = iPage.getRecords();
        List<user> users = new ArrayList<>();
        for (userGroup record : records) {
            String uid = record.getUid();
            user user = userDao.selectById(uid);
            users.add(user);
        }

        return users;
    }

    @Override
    public boolean changeGroupInfo(group group) {
        int i = groupDao.updateById(group);
        return i>0;
    }

    @Override
    public List<group> showGroupList(IPage page) {

        IPage iPage = groupDao.selectPage(page, null);
        List<group> records = iPage.getRecords();
        return records;
    }

    @Override
    public boolean leaveGroup(String gid, String uid) {
        LambdaQueryWrapper<userGroup> lqw = new LambdaQueryWrapper<userGroup>();
        lqw.eq(userGroup::getGid,gid);
        lqw.eq(userGroup::getUid,uid);
        int delete = userGroupDao.delete(lqw);
        return delete>0;
    }

    @Override
    public boolean changeGroupState(String gid, String uid, int state) {

        LambdaQueryWrapper<userGroup> lqw = new LambdaQueryWrapper<userGroup>();
        lqw.eq(userGroup::getGid,gid);
        lqw.eq(userGroup::getUid,uid);

        userGroup userGroup = new userGroup();
        userGroup.setState(state);
        int update = userGroupDao.update(userGroup, lqw);
        return update>0;
    }

    @Override
    public List<groupInvite> showInviteList(IPage page) {
        IPage iPage = groupInviteDao.selectPage(page, null);
        List<groupInvite> records = iPage.getRecords();
        return records;
    }

    @Override
    public boolean sendInvite(groupInvite groupInvite) {
        int insert = groupInviteDao.insert(groupInvite);
        return insert>0;
    }

    @Override
    public boolean respGroupInvite(String InviteId, Boolean agree) {

        groupInvite groupInvite = groupInviteDao.selectById(InviteId);
        if(agree){

            String rid = groupInvite.getReceiverId();
            String groupId = groupInvite.getGroupId();

            userGroup userGroup = new userGroup();
            userGroup.setGid(groupId);
            userGroup.setUid(rid);

            int insert = userGroupDao.insert(userGroup);

            groupInviteDao.deleteById(InviteId);
            return insert>0;
        }else {
            groupInviteDao.deleteById(InviteId);
            return false;
        }

    }

    @Override
    public boolean createGroup(String groupName,String uid) {
        LambdaQueryWrapper<group> lqw = new LambdaQueryWrapper<group>();
        lqw.eq(group::getGroupName,groupName);

        group group = new group();
        group.setGroupName(groupName);
        int insert = groupDao.insert(group);

        com.nart.pojo.group group1 = groupDao.selectOne(lqw);

        String id = group1.getId();
        userGroup userGroup = new userGroup();
        userGroup.setGid(id);
        userGroup.setUid(uid);

        int insert1 = userGroupDao.insert(userGroup);

        return insert1>0;
    }


}
