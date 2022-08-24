package com.nart.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("tb_group")
public class group {

    private String id;
    @TableField(value = "gname")
    private String groupName;

    private List<friend> userList;
    private String avatar;
    private int state;
    private String notice;

    @TableField(value = "last_level")
    private int userLevel;
    private List<message> chatHistory;
    private Boolean newMessage;
}
