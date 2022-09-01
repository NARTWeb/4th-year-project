package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.user;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;

import java.util.List;

public interface UserService {

    //    checkUserToken

    user findUser(String uname, String pwd);

    user findUserByName(String uname);

    boolean logout(String userId);

    user register(String email, String name, String pwd);

    user showUserInfo(String userId);

    user showUnameAvatar(String userId);

    boolean changeUserInfo(UserVo userVo,String id);

    IPage<user> searchNew(String name, PageVo pageVo);
}
