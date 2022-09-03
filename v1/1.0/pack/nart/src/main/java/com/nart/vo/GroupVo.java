package com.nart.vo;

import lombok.Data;

@Data
public class GroupVo {
    private String groupId;
    private String groupName;
    private String groupAvatar;
    private String notice;
    private Integer state;
    private Boolean newMsg;
}
