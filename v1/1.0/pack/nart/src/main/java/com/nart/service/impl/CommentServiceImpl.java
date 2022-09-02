package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nart.pojo.Comment;
import com.nart.pojo.User;
import com.nart.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private com.nart.dao.CommentDao CommentDao;

    @Autowired
    private com.nart.dao.UserDao UserDao;

    @Override
    public List<Comment> showCommentList(String statusId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<Comment>();

        lqw.eq(Comment::getStatusId, Integer.valueOf(statusId));
        List<Comment> Comments = CommentDao.selectList(lqw);
        for (Comment comment : Comments) {
            String userId = comment.getUserId();
            User user = UserDao.selectById(userId);
            String name = user.getName();
            comment.setUname(name);
        }

        return Comments;
    }

    @Override
    public boolean postComment(String statusId, String msg, String sid) {
        Comment Comment = new Comment();
        Comment.setMsg(msg);
        Comment.setStatusId(statusId);
        Comment.setUserId(sid);
        Long createTime = 1212121L;
        Comment.setCreateDate(createTime);
        return CommentDao.insert(Comment) > 0;
    }
}
