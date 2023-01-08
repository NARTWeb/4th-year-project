package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.User;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: UserService
 * @Description:
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface UserService {

    /**
     * @Description: This method allows the user to find the user by username and password
       @params: String uname, String pwd
      * @return: User
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User findUser(String uname, String pwd);

    /**
     * @Description: This method allows the user to find the user through the user name
       @params: String uname
      * @return: User
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User findUserByName(String uname);

    /**
     * @Description: This method changes the online status of a specific user
       @params: String userId
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean logout(String userId);

    /**
     * @Description: This method adds new users
       @params: String email, String name, String pwd
      * @return: User
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User register(String email, String name, String pwd);

    /**
     * @Description: This method displays information for a specific user
       @params: String userId
      * @return: User
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User showUserInfo(String userId);

    /**
     * @Description: This method displays the avatar of a specific user
       @params: String userId
      * @return: User
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User showUnameAvatar(String userId);

    /**
     * @Description: This method changes the information of a specific user
       @params: UserVo userVo,String id
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean changeUserInfo(UserVo userVo,String id);

    /**
     * @Description: This method searches for specific friends through the passed in parameters
       @params: String name, PageVo pageVo
      * @return: IPage<User>
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    IPage<User> searchNew(String name, PageVo pageVo);

    /**
     * @Description: This method changes the departure time of a specific user
       @params: String uid
      * @return: boolean
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    boolean upDatetime(String uid);

}
