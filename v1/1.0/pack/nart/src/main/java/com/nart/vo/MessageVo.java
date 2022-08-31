package com.nart.vo;

import lombok.Data;


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
    private String msgType;

}
