package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Friend;
import com.nart.pojo.User;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;

import java.util.List;

public interface UserService {

    //    checkUserToken

    User findUser(String uname, String pwd);

    User findUserByName(String uname);

    boolean logout(String userId);

    User register(String email, String name, String pwd);

    User showUserInfo(String userId);

    User showUnameAvatar(String userId);

    boolean changeUserInfo(UserVo userVo,String id);

    IPage<User> searchNew(String name, PageVo pageVo);

    boolean upDatetime(String uid);

}
