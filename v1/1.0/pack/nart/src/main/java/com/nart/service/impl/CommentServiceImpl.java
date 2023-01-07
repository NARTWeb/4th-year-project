package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nart.pojo.Comment;
import com.nart.pojo.User;
import com.nart.service.CommentService;
import com.nart.service.DataCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private com.nart.dao.CommentDao CommentDao;

    @Autowired
    private com.nart.dao.UserDao UserDao;

    @Autowired
    private DataCounterService dataCounterService;

    @Override
    public List<Comment> showCommentList(String statusId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<Comment>();
//
        lqw.eq(Comment::getStatusId,statusId);
        lqw.orderBy(true,false, Comment::getCreateDate);

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
        long l = System.currentTimeMillis();
        Long createTime = l;
        System.out.println(l);
        Comment.setCreateDate(createTime);
        dataCounterService.updateCommentAmount(true);
        return CommentDao.insert(Comment) > 0;
    }
}
