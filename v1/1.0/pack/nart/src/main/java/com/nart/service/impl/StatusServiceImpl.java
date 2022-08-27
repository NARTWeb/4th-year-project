package com.nart.service.impl;


import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.statusDao;
import com.nart.pojo.comment;
import com.nart.pojo.status;
import com.nart.service.CommentService;
import com.nart.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private statusDao StatusDao;

    @Autowired
    private CommentService commentService;

    @Override
    public IPage showStatusList(Long sid, IPage page) {
        LambdaQueryWrapper<status> lqw = new LambdaQueryWrapper<status>();
        lqw.eq(status::getSenderId, sid);
        IPage iPage = StatusDao.selectPage(page, lqw);
        List<status> records = iPage.getRecords();
        for (status record : records) {
            if(record.getLikes()!=0){
                record.setUserLike(true);
            }
            String id = record.getId();
            List<comment> comments = commentService.showCommentList(id);
            record.setCommentList(comments);

        }
        return iPage;
    }
}
