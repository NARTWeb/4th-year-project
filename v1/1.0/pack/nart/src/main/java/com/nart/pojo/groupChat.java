package com.nart.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_group_chat")
public class groupChat {
    private Long id;
    @TableField(value = "gid")
    private String groupId;
    @TableField(value = "sid")
    private String senderId;

    private String msg;
    private String type;
    private Long date;
    private int level;
}