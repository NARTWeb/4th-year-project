package com.nart.vo;

import lombok.Data;

@Data
public class RequestVo {
    private String id;
    private String friendId;
    private String msg;
    private String friendName;
    private String friendAvatar;
    private String senderName;

}
