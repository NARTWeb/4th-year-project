package com.nart.service.impl;

import com.nart.dao.FriendReqDao;
import com.nart.dao.GroupDao;
import com.nart.dao.StatusDao;
import com.nart.dao.UserDao;
import com.nart.pojo.FriendReq;
import com.nart.pojo.Group;
import com.nart.pojo.Status;
import com.nart.pojo.User;
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


    @Override
    public boolean LoadListUser(List<User> userList) {
        for (User user : userList) {
            int insert = userDao.insert(user);
            if(insert <= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean LoadListFriendReq(List<FriendReq> friendReqList) {
        for (FriendReq friendReq : friendReqList) {
            int insert = friendReqDao.insert(friendReq);
            if(insert <= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean LoadListStatus(List<Status> statusList) {
        for (Status status : statusList) {
            int insert = statusDao.insert(status);
            if(insert <= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean LoadListGroup(List<Group> groupList) {
        for (Group group : groupList) {
            int insert = groupDao.insert(group);
            if(insert <= 0){
                return false;
            }
        }
        return true;
    }
}
