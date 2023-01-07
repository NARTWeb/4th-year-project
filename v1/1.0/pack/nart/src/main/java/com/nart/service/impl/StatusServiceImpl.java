package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.FriendDao;
import com.nart.dao.StatusDao;
import com.nart.dao.UserDao;
import com.nart.pojo.*;
import com.nart.service.CommentService;
import com.nart.service.DataCounterService;
import com.nart.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Transactional
@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private com.nart.dao.StatusDao StatusDao;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DataCounterService dataCounterService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private StatusDao statusDao;

    @Override
    public List<Status> showStatusList(String sid, IPage page) {
        LambdaQueryWrapper<Status> lqw = new LambdaQueryWrapper<Status>();
        lqw.eq(Status::getSenderId, sid);
        lqw.orderBy(true,false, Status::getCreateDate);
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
    public List<Status> showAllStatusList(String uid) {
//        User user = userDao.selectById(uid);
        LambdaQueryWrapper<Friend> lqw = new LambdaQueryWrapper<Friend>();
        lqw.eq(Friend::getUid, uid);
        List<Friend> friends = friendDao.selectList(lqw);
        List<String> friendIds = new ArrayList<>();
        for (Friend friend : friends) {
            friendIds.add(friend.getFid());
        }
        friendIds.add(uid);
        List<Status> Allstatuses = new ArrayList<Status>();
        for (String friendId : friendIds) {
            LambdaQueryWrapper<Status> lqw1 = new LambdaQueryWrapper<Status>();
            lqw1.eq(Status::getSenderId, friendId);
            lqw1.orderBy(true,false, Status::getCreateDate);
            List<Status> statuses = statusDao.selectList(lqw1);
            for (Status status : statuses) {
                Allstatuses.add(status);
            }
        }
        Collections.sort(Allstatuses, (a, b) -> b.getCreateDate().compareTo(a.getCreateDate()));

        for (Status record : Allstatuses) {
            if(record.getLikes()!=0){
                record.setUserLike(true);
            }
            String id = record.getId();
            List<Comment> Comments = commentService.showCommentList(id);
            record.setCommentList(Comments);

        }
        return Allstatuses;
    }

    @Override
    public boolean postStatus(Status status) {
        int insert = StatusDao.insert(status);
        dataCounterService.updateStatusAmount(true);
        return insert>0;
    }

    @Override
    public boolean delStatus(String id) {
        int id1 = StatusDao.deleteById(id);
        dataCounterService.updateStatusAmount(false);
        return id1>0;
    }

    @Override
    public boolean likeStatus(String id, boolean like) {
        int i;
        if(like){
            Status status = StatusDao.selectById(id);
            status.setLikes(status.getLikes()+1);
            i = StatusDao.updateById(status);
            return i>0;
        }else{
            Status status = StatusDao.selectById(id);
            status.setLikes(status.getLikes()-1);
            i = StatusDao.updateById(status);
            return i>0;
        }

    }
}
