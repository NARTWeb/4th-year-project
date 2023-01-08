package com.nart.service;

import com.nart.pojo.*;

import java.util.List;
/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: LoadDataInDataBase
 * @Description: This class is used to add records of each table
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface LoadDataInDataBase {

    /**
     * @Description: This method is used to insert multiple user records into the user table
       @params: List<User> userList
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean LoadListUser(List<User> userList);

    /**
     * @Description: This method is used to insert multiple friend application records into the request table
       @params: List<FriendReq> friendReqList
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean LoadListFriendReq(List<FriendReq> friendReqList);

    /**
     * @Description: This method is used to insert multiple status records into the status table
       @params: List<Status> statusList
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean LoadListStatus(List<Status> statusList);

    /**
     * @Description: This method is used to insert multiple group records into the group table
       @params: List<Group> groupList
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean LoadListGroup(List<Group> groupList);

    /**
     * @Description: This method is used to insert multiple Comment records into the Comment table
       @params: List<Comment> commentList
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean LoadListComment(List<Comment> commentList);

    /**
     * @Description: This method is used to insert multiple GroupInvite records into the Invite table
       @params: List<GroupInvite> groupInviteList
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean LoadListGroupInvite(List<GroupInvite> groupInviteList);
}
