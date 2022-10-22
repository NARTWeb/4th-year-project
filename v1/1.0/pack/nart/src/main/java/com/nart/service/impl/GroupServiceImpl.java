package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.GroupDao;
import com.nart.dao.GroupInviteDao;
import com.nart.dao.UserDao;
import com.nart.dao.UserGroupDao;
import com.nart.pojo.*;
import com.nart.service.GroupService;
import com.nart.util.UserThreadLocal;
import com.nart.vo.GroupVo;
import com.nart.vo.InviteVo;
import com.nart.vo.UserVo;
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
    public List<UserVo> showGroupMebList(String gid) {
        LambdaQueryWrapper<UserGroup> lqw = new LambdaQueryWrapper<UserGroup>();
        lqw.eq(UserGroup::getGid,gid);

//        IPage iPage = userGroupDao.selectPage(null, lqw);
//        List<UserGroup> records = iPage.getRecords();
        List<UserGroup> userGroups = userGroupDao.selectList(lqw);
        List<User> Users = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            String uid = userGroup.getUid();
            User user = userDao.selectById(uid);
            String name = user.getName();
            String avatar = user.getAvatar();
            String id = user.getId();

            User user1 = new User();
            user1.setId(id);
            user1.setAvatar(avatar);
            user1.setName(name);
            Users.add(user1);
        }

//        for (UserGroup record : records) {
//            String uid = record.getUid();
//            User user = userDao.selectById(uid);
//            Users.add(user);
//        }

        UserVo userVo = new UserVo();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : Users) {
            UserVo transfer = userVo.transfer(user);
            userVos.add(transfer);
        }

        return userVos;
    }

    @Override
    public boolean changeGroupInfo(Group group) {
        int i = groupDao.updateById(group);
        return i>0;
    }

    @Override
    public List<GroupVo> showGroupList(IPage page) {
        LambdaQueryWrapper<Group> lqw = new LambdaQueryWrapper<Group>();
        lqw.orderBy(true,false, Group::getUserLevel);

        IPage iPage = groupDao.selectPage(page, lqw);
        List<Group> records = iPage.getRecords();
        List<GroupVo> groupVos = new ArrayList<>();
        GroupVo groupVo = new GroupVo();
        for (Group record : records) {
            GroupVo transfer = groupVo.transfer(record);
            groupVos.add(transfer);
        }
        return groupVos;
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
    public List<InviteVo> showInviteList(IPage page) {
        String id = UserThreadLocal.get().getId();
        LambdaQueryWrapper<GroupInvite> lqw = new LambdaQueryWrapper<GroupInvite>();
        lqw.eq(GroupInvite::getReceiverId,id);
        lqw.orderBy(true,false, GroupInvite::getDate);

        IPage iPage = groupInviteDao.selectPage(page, lqw);
        List<GroupInvite> records = iPage.getRecords();

        InviteVo inviteVo = new InviteVo();
        List<InviteVo> inviteVos =  new ArrayList<>();


        for (GroupInvite record : records) {
            InviteVo transfer = inviteVo.transfer(record);

            Group group = groupDao.selectById(record.getGroupId());
            transfer.setGroupName(group.getGroupName());
            transfer.setReceiverId(record.getReceiverId());
            transfer.setGroupAvatar(group.getAvatar());


            User user = userDao.selectById(record.getSenderId());
            transfer.setSenderName(user.getName());


            inviteVos.add(transfer);
        }


        return inviteVos;
    }

    @Override
    public boolean sendInvite(GroupInvite groupInvite) {
        groupInvite.setSenderId(UserThreadLocal.get().getId());
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
