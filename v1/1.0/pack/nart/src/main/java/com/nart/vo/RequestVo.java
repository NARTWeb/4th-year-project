package com.nart.vo;

import com.nart.dao.UserDao;
import com.nart.pojo.FriendReq;
import com.nart.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class RequestVo {
    private String id;
    private String friendId;
    private String msg;
    private String friendName;
    private String friendAvatar;
    private String senderName;
    @Autowired
    private UserDao userDao;

    public RequestVo transfer(FriendReq friendReq){
        RequestVo requestVo = new RequestVo();
        requestVo.setId(friendReq.getId());
        requestVo.setFriendId(friendReq.getReceiverId());
        requestVo.setMsg(friendReq.getMsg());
        User user = userDao.selectById(friendReq.getReceiverId());
        requestVo.setFriendName(user.getName());
        requestVo.setFriendAvatar(user.getAvatar());
        User user1 = userDao.selectById(friendReq.getSenderId());
        requestVo.setSenderName(user1.getName());
        return requestVo;
    }

}
