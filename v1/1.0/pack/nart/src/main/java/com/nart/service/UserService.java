package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.user;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;

import java.util.List;

public interface UserService {

    //    checkUserToken

    boolean login(String uname,String pwd);

    boolean logout(String userId);

    boolean register(String email,String name, String pwd);

    user showUserInfo(String userId);

    List<String> showUnameAvatar(String userId);

    boolean changeUserInfo(UserVo userVo,String id);

    IPage<user> searchNew(String name, PageVo pageVo);
}
