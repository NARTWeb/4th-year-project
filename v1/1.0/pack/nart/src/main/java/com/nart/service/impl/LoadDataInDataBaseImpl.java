package com.nart.service.impl;

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

    @Override
    public boolean LoadListUser(List<User> userList) {
        for (User user : userList) {
            userDao.insert(user);
        }
        return true;
    }

    @Override
    public boolean LoadListFriendReq(List<FriendReq> friendReqList) {
        return false;
    }

    @Override
    public boolean LoadListStatus(List<Status> statusList) {
        return false;
    }

    @Override
    public boolean LoadListGroup(List<Group> groupList) {
        return false;
    }
}
