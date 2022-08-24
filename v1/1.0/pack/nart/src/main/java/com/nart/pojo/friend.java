package com.nart.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("tb_friend")
public class friend {
    private String id;
    private String name;
    private String email;
    private String avatar;
    private Boolean online;

    private String state;
//    private Chat chat;

    @TableField(exist = false)
    private List<status> statusList;
    @TableField(exist = false)
    private List<message> chatHistory;
    @TableField(exist = false)
    private Boolean newMessage;


}
