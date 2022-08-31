package com.nart.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@TableName("tb_user")
public class user {
// 主键生成策略
//    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String pwd;
    private String avatar;
    @TableField(value = "uname")
    private String name;
    private String age;
    private String tel;
    private String address;
    private String email;

    @TableField(value = "on_line")
    private Boolean userOnline;

    @TableField(exist = false)
    private List<friend> friendList;
    @TableField(exist = false)
    private List<friendReq> requestList;
    @TableField(exist = false)
    private List<group> groupList;
    @TableField(exist = false)
    private List<groupInvite> groupInvites;
    @TableField(exist = false)
    private List<status> statusList;
}
