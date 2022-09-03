package com.nart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.FriendDao;
import com.nart.dao.FriendReqDao;
import com.nart.dao.StatusDao;
import com.nart.dao.UserDao;
import com.nart.pojo.Friend;
import com.nart.pojo.FriendReq;
import com.nart.pojo.User;
import com.nart.service.ChatService;
import com.nart.service.FriendService;
import com.nart.service.StatusService;
import com.nart.service.UserService;
import com.nart.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StatusDao statusDao;
    @Autowired
    private StatusService statusService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendReqDao friendReqDAO;


    @Override
    public List<Friend> showFriendList(IPage page, String userId) {

        LambdaQueryWrapper<Friend> lqw = new LambdaQueryWrapper<Friend>();
        lqw.eq(Friend::getUid, userId);
        IPage iPage = friendDao.selectPage(page, lqw);
        List<Friend> records = iPage.getRecords();
        for (Friend record : records) {
            String fid = record.getFid();
            User user = userDao.selectById(fid);
            record.setName(user.getName());
            record.setEmail(user.getEmail());
            record.setAvatar(user.getAvatar());
            int userOnline = user.getUserOnline();
            if(userOnline==1){
                record.setOnline(true);
            }else {
                record.setOnline(false);
            }

            record.setStatusList(statusService.showStatusList(userId ,page));
            record.setChatHistory(chatService.showFriendHistory(user.getId(),page));

        }


        return records;
    }

    @Override
    public List<User> searchFriend(String name, IPage page) {
        PageVo pageVo = new PageVo();
        pageVo.setPageNum((int) page.getCurrent());
        pageVo.setPageSize((int) page.getSize());
        IPage<User> userIPage = userService.searchNew(name, pageVo);
        List<User> records = userIPage.getRecords();
        return records;
    }

    @Override
    public boolean delFriend(String fid,String uid) {
        LambdaQueryWrapper<Friend> lqw = new LambdaQueryWrapper<Friend>();
        lqw.eq(Friend::getFid, fid);
        lqw.eq(Friend::getUid, uid);
        int delete = friendDao.delete(lqw);

        return delete>0;
    }

    @Override
    public boolean changeFriendState(String fid, String uid,int state) {
        LambdaQueryWrapper<Friend> lqw = new LambdaQueryWrapper<Friend>();
        lqw.eq(Friend::getFid, fid);
        lqw.eq(Friend::getUid, uid);


        Friend friend = new Friend();
        friend.setState(String.valueOf(state));
        int update = friendDao.update(friend, lqw);
        return update>0;
    }

    @Override
    public List<FriendReq> showReqList(IPage page, String sid) {
        LambdaQueryWrapper<FriendReq> lqw = new LambdaQueryWrapper<FriendReq>();
        lqw.eq(FriendReq::getReceiverId, sid);
        IPage iPage = friendReqDAO.selectPage(page, lqw);
        List<FriendReq> records = iPage.getRecords();
        return records;
    }

    @Override
    public boolean sendFriendReq(String rid, String sid, String msg) {

        FriendReq friendReq = new FriendReq();
        friendReq.setMsg(msg);
        friendReq.setReceiverId(rid);
        friendReq.setSenderId(sid);
        int insert = friendReqDAO.insert(friendReq);

        return insert>0;
    }

    @Override
    public boolean respFriendReq(String reqId, Boolean agree) {
        FriendReq friendReq = friendReqDAO.selectById(reqId);

        if(agree){
            String senderId = friendReq.getSenderId();
            String ReceiverId = friendReq.getReceiverId();

            Friend friend = new Friend();
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
    public List<User> searchNew(String name, PageVo pageVo) {
        IPage<User> userIPage = userService.searchNew(name, pageVo);
        List<User> records = userIPage.getRecords();
        return records;
    }
}
