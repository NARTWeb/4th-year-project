package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.Comment;
import com.nart.pojo.Status;
import com.nart.service.CommentService;
import com.nart.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private com.nart.dao.StatusDao StatusDao;

    @Autowired
    private CommentService commentService;

    @Override
    public List<Status> showStatusList(Long sid, IPage page) {
        LambdaQueryWrapper<Status> lqw = new LambdaQueryWrapper<Status>();
        lqw.eq(Status::getSenderId, sid);
        IPage iPage = StatusDao.selectPage(page, lqw);
        List<Status> records = iPage.getRecords();
        for (Status record : records) {
            if(record.getLikes()!=0){
                record.setUserLike(true);
            }
            String id = record.getId();
            List<Comment> Comments = commentService.showCommentList(id);
            record.setCommentList(Comments);

        }
        return records;
    }

    @Override
    public boolean postStatus(Status status) {
        int insert = StatusDao.insert(status);
        return insert>0;
    }

    @Override
    public boolean delStatus(String id) {
        int id1 = StatusDao.deleteById(id);
        return id1>0;
    }

    @Override
    public boolean likeStatus(String id, boolean like) {
        int i;
        if(like){
            Status status = StatusDao.selectById(id);
            status.setLikes(1);
            i = StatusDao.updateById(status);
            return i>0;
        }
        return false;
    }
}
