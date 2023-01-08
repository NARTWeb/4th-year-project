package com.nart.service;

import com.nart.pojo.Comment;
import com.nart.pojo.Status;
import com.nart.pojo.User;
import com.nart.util.Result;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: AdminService
 * @Description: This class provides various operations for administrators
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface AdminService {

    /**
     * @Description: This method displays all user information
     * @return: List<User>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<User> showAllUserInfo();

    /**
     * @Description: This method displays the number of all users
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int showAllUserNum();

    /**
     * @Description: This method displays all online users
      * @return: List<User>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<User> showOnlineUserInfo();

    /**
     * @Description: This method displays the number of all online users
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int showOnlineUserNum();

    /**
     * @Description: This method displays all status information
      * @return: List<Status>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<Status> showAllStatusInfo();

    /**
     * @Description: This method displays the number of all statuses
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int showAllStatusNum();

    /**
     * @Description:  This method displays all comment information
      * @return: List<Comment>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    List<Comment> showAllCommentInfo();

    /**
     * @Description: This method displays the number of all comments
      * @return: int
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    int showAllCommentNum();


    /**
     * @Description:  This method searches for specific users
       @param id user id
      * @return: User
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User searchUser(String id);

    /**
     * @Description: This method changes the user state
       @param id user id
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean blockUser(String id);

    /**
     * @Description: This method deletes specific status
       @param id status id
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean deleteStatus(String id);

    /**
     * @Description: This method deletes a specific comment
       @param id comment id
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean deleteComment(String id);

    Result logOut();

    Result logIn();

    boolean checkAdmin();

    boolean setonline(String id);
}
