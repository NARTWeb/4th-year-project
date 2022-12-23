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




}
