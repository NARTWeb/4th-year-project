package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.*;
import com.nart.pojo.*;
import com.nart.service.*;
import com.nart.util.RedisUtil;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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
    private CommentDao commentDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private StatusDao statusDao;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DataCounterService dataCounterService;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private  FriendDao friendDao;

    @Autowired
    private UserGroupDao userGroupDao;

    @Autowired
    private StatusService statusService;




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
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getUserOnline, 1);
        List<User> users = userDao.selectList(lqw);
        int size = users.size();
        return size;
    }

    @Override
    public List<Status> showAllStatusInfo() {
        LambdaQueryWrapper<Status> lqw1 = new LambdaQueryWrapper<Status>();
        lqw1.orderBy(true,false, Status::getCreateDate);
        List<Status> statuses = statusDao.selectList(lqw1);
        for (Status status : statuses) {
            String id = status.getId();
            List<Comment> comments = commentService.showCommentList(id);
            status.setCommentList(comments);
        }
        return statuses;
    }

    @Override
    public int showAllStatusNum() {
        List<Status> statuses = statusDao.selectList(null);
        int size = statuses.size();
        return size;
    }

    @Override
    public List<Comment> showAllCommentInfo() {
        List<Comment> comments = commentDao.selectList(null);
        return comments;
    }

    @Override
    public int showAllCommentNum() {
        List<Comment> comments = commentDao.selectList(null);
        return comments.size();
    }

    @Override
    public User searchUser(String id) {
        User user = userDao.selectById(id);
        return user;
    }

    @Override
    public boolean blockUser(String id) {
        User user = userDao.selectById(id);
        user.setState(1);
        int i = userDao.updateById(user);
        return i>0;
    }


    @Override
    public boolean deleteStatus(String id) {
        Status status = statusDao.selectById(id);
        String id1 = status.getId();
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<Comment>();
        lqw.eq(Comment::getStatusId, id1);
        List<Comment> comments = commentDao.selectList(lqw);
        for (Comment comment : comments) {
            commentDao.deleteById(comment.getId());
        }
        int i = statusDao.deleteById(id1);
        return i>0;
    }

    @Override
    public boolean deleteComment(String id) {

        int i = commentDao.deleteById(id);
        return i>0;
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
