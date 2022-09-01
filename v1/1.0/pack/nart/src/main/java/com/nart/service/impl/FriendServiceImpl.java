package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.dao.friendDao;
import com.nart.dao.friendReqDAO;
import com.nart.dao.statusDao;
import com.nart.dao.userDao;
import com.nart.pojo.friend;
import com.nart.pojo.friendReq;
import com.nart.pojo.user;
import com.nart.service.ChatService;
import com.nart.service.FriendService;
import com.nart.service.StatusService;
import com.nart.service.UserService;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private friendDao friendDao;

    @Autowired
    private userDao userDao;

    @Autowired
    private statusDao statusDao;
    @Autowired
    private StatusService statusService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private friendReqDAO friendReqDAO;


    @Override
    public List<friend> showFriendList(IPage page,String userId) {

        LambdaQueryWrapper<friend> lqw = new LambdaQueryWrapper<friend>();
        lqw.eq(friend::getUid, userId);
        IPage iPage = friendDao.selectPage(page, lqw);
        List<friend> records = iPage.getRecords();
        for (friend record : records) {
            String fid = record.getFid();
            user user = userDao.selectById(fid);
            record.setName(user.getName());
            record.setEmail(user.getEmail());
            record.setAvatar(user.getAvatar());
            int userOnline = user.getUserOnline();
            if(userOnline==1){
                record.setOnline(true);
            }else {
                record.setOnline(false);
            }

            record.setStatusList(statusService.showStatusList(Long.valueOf(userId) ,page));
            record.setChatHistory(chatService.showFriendHistory(user.getId()));

        }


        return records;
    }

    @Override
    public List<user> searchFriend(String name, IPage page) {
        PageVo pageVo = new PageVo();
        pageVo.setPageNum((int) page.getCurrent());
        pageVo.setPageSize((int) page.getSize());
        IPage<user> userIPage = userService.searchNew(name, pageVo);
        List<user> records = userIPage.getRecords();
        return records;
    }

    @Override
    public boolean delFriend(String fid,String uid) {
        LambdaQueryWrapper<friend> lqw = new LambdaQueryWrapper<friend>();
        lqw.eq(friend::getFid, fid);
        lqw.eq(friend::getUid, uid);
        int delete = friendDao.delete(lqw);

        return delete>0;
    }

    @Override
    public boolean changeFriendState(String fid, String uid,int state) {
        LambdaQueryWrapper<friend> lqw = new LambdaQueryWrapper<friend>();
        lqw.eq(friend::getFid, fid);
        lqw.eq(friend::getUid, uid);


        friend friend = new friend();
        friend.setState(String.valueOf(state));
        int update = friendDao.update(friend, lqw);
        return update>0;
    }

    @Override
    public List<friendReq> showReqList(IPage page, String sid) {
        LambdaQueryWrapper<friendReq> lqw = new LambdaQueryWrapper<friendReq>();
        lqw.eq(friendReq::getSenderId, sid);
        IPage iPage = friendReqDAO.selectPage(page, lqw);
        List<friendReq> records = iPage.getRecords();
        return records;
    }

    @Override
    public boolean sendFriendReq(String rid, String sid, String msg) {

        friendReq friendReq = new friendReq();
        friendReq.setMsg(msg);
        friendReq.setReceiverId(rid);
        friendReq.setSenderId(sid);
        int insert = friendReqDAO.insert(friendReq);

        return insert>0;
    }

    @Override
    public boolean respFriendReq(String reqId, Boolean agree) {
        friendReq friendReq = friendReqDAO.selectById(reqId);

        if(agree){
            String senderId = friendReq.getSenderId();
            String ReceiverId = friendReq.getReceiverId();

            friend friend = new friend();
            friend.setFid(senderId);
            friend.setUid(ReceiverId);

            friendDao.insert(friend);
            friendReqDAO.deleteById(reqId);
            return true;
        }else{

            friendReqDAO.deleteById(reqId);
            return false;
        }
    }

    @Override
    public List<user> searchNew(String name, PageVo pageVo) {
        IPage<user> userIPage = userService.searchNew(name, pageVo);
        List<user> records = userIPage.getRecords();
        return records;
    }
}
