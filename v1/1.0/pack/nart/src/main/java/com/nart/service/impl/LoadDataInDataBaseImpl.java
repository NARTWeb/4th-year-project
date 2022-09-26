package com.nart.service.impl;

import com.nart.dao.*;
import com.nart.pojo.*;
import com.nart.service.LoadDataInDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadDataInDataBaseImpl implements LoadDataInDataBase {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FriendReqDao friendReqDao;
    @Autowired
    private StatusDao statusDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private GroupInviteDao groupInviteDao;


    @Override
    public boolean LoadListUser(List<User> userList) {

        for (User user : userList) {
            int insert = userDao.insert(user);
        }
        return true;
    }

    @Override
    public boolean LoadListFriendReq(List<FriendReq> friendReqList) {
        for (FriendReq friendReq : friendReqList) {
            int insert = friendReqDao.insert(friendReq);
        }
        return true;
    }

    @Override
    public boolean LoadListStatus(List<Status> statusList) {
        for (Status status : statusList) {
            int insert = statusDao.insert(status);

        }
        return true;
    }

    @Override
    public boolean LoadListGroup(List<Group> groupList) {
        for (Group group : groupList) {
            int insert = groupDao.insert(group);

        }
        return true;
    }

    @Override
    public boolean LoadListComment(List<Comment> commentList) {
        for (Comment comment : commentList) {
            int insert = commentDao.insert(comment);

        }

        return true;

    }

    @Override
    public boolean LoadListGroupInvite(List<GroupInvite> groupInviteList) {

        for (GroupInvite groupInvite : groupInviteList) {
            int insert = groupInviteDao.insert(groupInvite);

        }
        return true;
    }
}
