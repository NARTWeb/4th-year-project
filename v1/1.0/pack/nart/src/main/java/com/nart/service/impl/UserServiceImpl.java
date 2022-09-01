package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.dao.userDao;
import com.nart.pojo.user;
import com.nart.service.DataCounterService;
import com.nart.service.UserService;
import com.nart.vo.PageVo;
import com.nart.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private userDao UserDao;
    @Autowired
    private DataCounterService dataCounterService;


    @Override
    public user findUser(String uname, String pwd) {
        LambdaQueryWrapper<user> lqw = new LambdaQueryWrapper<user>();
        lqw.eq(user::getName,uname).eq(user::getPwd, pwd);
        user user = UserDao.selectOne(lqw);

        if(user != null){
            user.setUserOnline(1);
            UserDao.updateById(user);
            return user;
        }
        return null;
    }

    @Override
    public user findUserByName(String uname) {
        LambdaQueryWrapper<user> lqw = new LambdaQueryWrapper<user>();
        lqw.eq(user::getName,uname);
        return UserDao.selectOne(lqw);
    }

    @Override
    public boolean logout(String userId) {

        user user1 = UserDao.selectById(userId);
        int userOnline = user1.getUserOnline();
        if(userOnline == 1) {
            user user = new user();

            user.setId(userId);
            user.setUserOnline(0);
            int i = UserDao.updateById(user);
            return i > 0;
        }
        return false;
    }

    @Override
    public user register(String email, String name, String pwd) {
        user user = new user();
        user.setEmail(email);
        user.setName(name);
        user.setPwd(pwd);
        user.setUserOnline(0);
        user.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
        int insert = UserDao.insert(user);
        if(insert>0) return user;
        return null;
    }

    @Override
    public user showUserInfo(String userId) {
        user user = UserDao.selectById(userId);
        return user;
    }

    @Override
    public user showUnameAvatar(String userId) {
        LambdaQueryWrapper<user> lqw = new LambdaQueryWrapper<user>();
        lqw.select(user::getName, user::getAvatar).eq(user::getId, userId);
        return UserDao.selectOne(lqw);
    }

    @Override
    public boolean changeUserInfo(UserVo userVo,String id) {
        user user = UserDao.selectById(id);

        user.setName(userVo.getUname());
        user.setPwd(userVo.getPwd());
        user.setAvatar(userVo.getAvatar());
        user.setTel(userVo.getPhone());
        user.setEmail(userVo.getEmail());
        user.setAddress(userVo.getAddress());
        user.setAge(userVo.getBirthday());
        int i = UserDao.updateById(user);
        return i>0;
    }

    @Override
    public IPage<user> searchNew(String name, PageVo pageVo) {

        IPage<user> page = new Page<>(pageVo.getPageNum(), pageVo.getPageSize());
        LambdaQueryWrapper<user> lqw = new LambdaQueryWrapper<user>();
        lqw.like(user::getName, name);
        IPage<user> userIPage1 = UserDao.selectPage(page, lqw);
        return userIPage1;
    }
}
