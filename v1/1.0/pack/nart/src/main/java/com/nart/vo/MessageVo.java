package com.nart.vo;

import com.nart.dao.UserDao;
import com.nart.pojo.FriendChat;
import com.nart.pojo.GroupChat;
import com.nart.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class MessageVo {
    private String msgId;
    private String type;
    private String chatId;
    private String senderId;
    private String senderName;
    private String senderAvatar;
    private DateVo sendDate;
    private String msg;
    @Autowired
    private UserDao userDao;

    public MessageVo FriendChat_transfer(FriendChat friendChat){
        MessageVo messageVo = new MessageVo();
        messageVo.setMsgId(String.valueOf(friendChat.getId()));
        messageVo.setType(friendChat.getType());
        messageVo.setChatId(friendChat.getReceiverId());
        messageVo.setSenderId(friendChat.getSenderId());
        User user = userDao.selectById(friendChat.getSenderId());

        messageVo.setSenderName(user.getName());
        messageVo.setSenderAvatar(user.getAvatar());
        DateVo dateVo = new DateVo();
        DateVo dateToString = dateVo.getDateToString(friendChat.getDate());
        messageVo.setSendDate(dateToString);

        messageVo.setMsg(friendChat.getMsg());

        return messageVo;
    }

    public MessageVo GroupChat_transfer(GroupChat groupChat){
        MessageVo messageVo = new MessageVo();
        messageVo.setMsgId(String.valueOf(groupChat.getId()));
        messageVo.setType(groupChat.getType());
        messageVo.setChatId(groupChat.getGroupId());
        messageVo.setSenderId(groupChat.getSenderId());
        User user = userDao.selectById(groupChat.getSenderId());
        messageVo.setSenderName(user.getName());
        messageVo.setSenderAvatar(user.getAvatar());
        DateVo dateVo = new DateVo();
        DateVo dateToString = dateVo.getDateToString(groupChat.getDate());
        messageVo.setSendDate(dateToString);
        messageVo.setMsg(groupChat.getMsg());
        return messageVo;
    }

}
