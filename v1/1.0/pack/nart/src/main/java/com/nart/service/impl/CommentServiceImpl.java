package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nart.dao.commentDao;
import com.nart.dao.userDao;
import com.nart.pojo.comment;
import com.nart.pojo.user;
import com.nart.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private commentDao CommentDao;

    @Autowired
    private userDao UserDao;

    @Override
    public List<comment> showCommentList(String statusId) {
        LambdaQueryWrapper<comment> lqw = new LambdaQueryWrapper<comment>();

        lqw.eq(comment::getStatusId, Integer.valueOf(statusId));
        List<comment> comments = CommentDao.selectList(lqw);
        for (comment comment : comments) {
            String userId = comment.getUserId();
            user user = UserDao.selectById(userId);
            String name = user.getName();
            comment.setUname(name);
        }

        return comments;
    }

    @Override
    public boolean postComment(String statusId, String msg, String sid) {
        comment Comment = new comment();
        Comment.setMsg(msg);
        Comment.setStatusId(statusId);
        Comment.setUserId(sid);
        Long createTime = 1212121L;
        Comment.setCreateDate(createTime);
        return CommentDao.insert(Comment)>0;
    }
}
