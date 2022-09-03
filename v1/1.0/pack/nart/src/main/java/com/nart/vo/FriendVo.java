package com.nart.vo;

import lombok.Data;

@Data
public class FriendVo {
    private String id;
    private String uname;
    private String avatar;
    private Integer state;
    private Boolean newMsg;
}
