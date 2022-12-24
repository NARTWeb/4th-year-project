package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.FriendDao;
import com.nart.dao.GroupDao;
import com.nart.dao.UserDao;
import com.nart.dao.UserGroupDao;
import com.nart.pojo.*;
import com.nart.service.AdminService;
import com.nart.service.ChatService;
import com.nart.service.DataCounterService;
import com.nart.util.RedisUtil;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private com.nart.dao.FriendChatDao FriendChatDao;

    @Autowired
    private com.nart.dao.GroupChatDao GroupChatDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private DataCounterService dataCounterService;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private  FriendDao friendDao;

    @Autowired
    private UserGroupDao userGroupDao;


    @Override
    public List<User> showAllUserInfo() {
        List<User> users = userDao.selectList(null);
        return users;
    }

    @Override
    public int showAllUserNum() {
        List<User> users = userDao.selectList(null);
        int size = users.size();
        return size;
    }

    @Override
    public List<User> showOnlineUserInfo() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getUserOnline, 1);
        List<User> users = userDao.selectList(lqw);

        return users;
    }

    @Override
    public int showOnlineUserNum() {
        return 0;
    }

    @Override
    public List<Status> showAllStatusInfo() {
        return null;
    }

    @Override
    public int showAllStatusNum() {
        return 0;
    }

    @Override
    public List<Comment> showAllCommentInfo() {
        return null;
    }

    @Override
    public int showAllCommentNum() {
        return 0;
    }

    @Override
    public User searchUser(String id) {
        return null;
    }

    @Override
    public boolean blockUser(String id) {
        return false;
    }

    @Override
    public boolean deleteStatus(String id) {
        return false;
    }

    @Override
    public boolean deleteComment(String id) {
        return false;
    }

    @Override
    public Result logOut() {
        return null;
    }

    @Override
    public Result logIn() {
        return null;
    }

    @Override
    public boolean checkAdmin() {
        return false;
    }
}
