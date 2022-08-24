package com.nart.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

public class dataCounter {

    @TableField(value = "user_amount")
    private int userAmount;
    @TableField(value = "user_online")
    private int userOnline;
    @TableField(value = "status_amount")
    private int statusAmount;
    @TableField(value = "comment_amount")
    private int commentAmount;
    @TableField(value = "msg_amount")
    private int msgAmount;
}
