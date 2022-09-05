package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.GroupDao;
import com.nart.dao.GroupInviteDao;
import com.nart.dao.UserDao;
import com.nart.dao.UserGroupDao;
import com.nart.pojo.Group;
import com.nart.pojo.GroupInvite;
import com.nart.pojo.User;
import com.nart.pojo.UserGroup;
import com.nart.service.GroupService;
import com.nart.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private UserGroupDao userGroupDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupInviteDao groupInviteDao;

    @Override
    public List<User> showGroupMebList(String gid) {
        LambdaQueryWrapper<UserGroup> lqw = new LambdaQueryWrapper<UserGroup>();
        lqw.eq(UserGroup::getGid,gid);

        IPage iPage = userGroupDao.selectPage(null, lqw);
        List<UserGroup> records = iPage.getRecords();
        List<User> Users = new ArrayList<>();
        for (UserGroup record : records) {
            String uid = record.getUid();
            User user = userDao.selectById(uid);
            Users.add(user);
        }

        return Users;
    }

    @Override
    public boolean changeGroupInfo(Group group) {
        int i = groupDao.updateById(group);
        return i>0;
    }

    @Override
    public List<Group> showGroupList(IPage page) {

        IPage iPage = groupDao.selectPage(page, null);
        List<Group> records = iPage.getRecords();
        return records;
    }

    @Override
    public boolean leaveGroup(String gid, String uid) {
        LambdaQueryWrapper<UserGroup> lqw = new LambdaQueryWrapper<UserGroup>();
        lqw.eq(UserGroup::getGid,gid);
        lqw.eq(UserGroup::getUid,uid);
        int delete = userGroupDao.delete(lqw);
        return delete>0;
    }

    @Override
    public boolean changeGroupState(String gid, String uid, int state) {

        LambdaQueryWrapper<UserGroup> lqw = new LambdaQueryWrapper<UserGroup>();
        lqw.eq(UserGroup::getGid,gid);
        lqw.eq(UserGroup::getUid,uid);

        UserGroup userGroup = new UserGroup();
        userGroup.setState(state);
        int update = userGroupDao.update(userGroup, lqw);
        return update>0;
    }

    @Override
    public List<GroupInvite> showInviteList(IPage page) {
        String id = UserThreadLocal.get().getId();
        LambdaQueryWrapper<GroupInvite> lqw = new LambdaQueryWrapper<GroupInvite>();
        lqw.eq(GroupInvite::getReceiverId,id);

        IPage iPage = groupInviteDao.selectPage(page, lqw);
        List<GroupInvite> records = iPage.getRecords();
        return records;
    }

    @Override
    public boolean sendInvite(GroupInvite groupInvite) {
        int insert = groupInviteDao.insert(groupInvite);
        return insert>0;
    }

    @Override
    public boolean respGroupInvite(String InviteId, Boolean agree) {

        GroupInvite groupInvite = groupInviteDao.selectById(InviteId);
        if(agree){

            String rid = groupInvite.getReceiverId();
            String groupId = groupInvite.getGroupId();

            UserGroup userGroup = new UserGroup();
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
        LambdaQueryWrapper<Group> lqw = new LambdaQueryWrapper<Group>();
        lqw.eq(Group::getGroupName,groupName);

        Group group = new Group();
        group.setGroupName(groupName);
        int insert = groupDao.insert(group);

        Group group1 = groupDao.selectOne(lqw);

        String id = group1.getId();
        UserGroup userGroup = new UserGroup();
        userGroup.setGid(id);
        userGroup.setUid(uid);
        userGroup.setJoinLevel(1);

        int insert1 = userGroupDao.insert(userGroup);

        return insert1>0;
    }

    @Override
    public boolean joinGroup(String groupId) {
        Group group = groupDao.selectById(groupId);
        int userLevel = group.getUserLevel();

        String id = UserThreadLocal.get().getId();
        UserGroup userGroup = new UserGroup();
        userGroup.setGid(groupId);
        userGroup.setUid(id);
        userGroup.setJoinLevel(userLevel+1);

        int insert = userGroupDao.insert(userGroup);

        group.setUserLevel(userLevel+1);
        groupDao.updateById(group);


        return insert>0;
    }

    @Override
    public Set<String> findAllMembers(String gid) {
        LambdaQueryWrapper<UserGroup> lqw = new LambdaQueryWrapper<UserGroup>();
        lqw.eq(UserGroup::getGid,gid);

        List<UserGroup> userGroups = userGroupDao.selectList(lqw);
        Set<String> ids = new HashSet<>();
        for (UserGroup record : userGroups) {
            String uid = record.getUid();
            ids.add(uid);
        }

        return ids;
    }


}
