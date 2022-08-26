package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nart.dao.commentDao;
import com.nart.pojo.comment;
import com.nart.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private commentDao CommentDao;

    @Override
    public List<comment> showCommentList(String statusId) {
        LambdaQueryWrapper<comment> lqw = new LambdaQueryWrapper<comment>();

        lqw.eq(comment::getStatusId, Integer.valueOf(statusId));
        List<comment> comments = CommentDao.selectList(lqw);
        return comments;
    }

    @Override
    public boolean postComment(String statusId, String msg) {
        return false;
    }
}
