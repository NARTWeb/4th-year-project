package com.nart.vo;

import lombok.Data;

import java.util.List;

@Data
public class StatusVo {
    private String uid;
    private String uname;
    private String avatar;
    private String statusId;
    private DateVo createDate;
    private int likes;
    private Boolean liked;
    private String msg;
    private String pics;
    private List<CommentVo> comments;

}
