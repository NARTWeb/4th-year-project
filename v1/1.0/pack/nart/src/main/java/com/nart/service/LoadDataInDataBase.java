package com.nart.service;

import com.nart.pojo.FriendReq;
import com.nart.pojo.Group;
import com.nart.pojo.Status;
import com.nart.pojo.User;

import java.util.List;

public interface LoadDataInDataBase {

    boolean LoadListUser(List<User> userList);

    boolean LoadListFriendReq(List<FriendReq> friendReqList);

    boolean LoadListStatus(List<Status> statusList);

    boolean LoadListGroup(List<Group> groupList);


}
