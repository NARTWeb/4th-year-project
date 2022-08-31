package com.nart.vo;

import lombok.Data;

@Data
public class CommentVo {
    private String commentId;
    private String uname;
    private String msg;
    private DateVo createDate;
}
