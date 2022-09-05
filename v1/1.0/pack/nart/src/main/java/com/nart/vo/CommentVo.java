package com.nart.vo;

import lombok.Data;

@Data
public class CommentVo {
    private String statusId;
    private String uname;
    private String msg;
    private DateVo createDate;
}
