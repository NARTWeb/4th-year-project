package com.nart.service;

import com.nart.pojo.*;

import java.util.List;

public interface LoadDataInDataBase {

    boolean LoadListUser(List<User> userList);

    boolean LoadListFriendReq(List<FriendReq> friendReqList);

    boolean LoadListStatus(List<Status> statusList);

    boolean LoadListGroup(List<Group> groupList);

    boolean LoadListComment(List<Comment> commentList);

    boolean LoadListGroupInvite(List<GroupInvite> groupInviteList);
}
