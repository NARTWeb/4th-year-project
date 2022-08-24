package com.nart.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("tb_status")
public class status {

    private String id;
    @TableField(value = "sid")
    private String senderId;
    private String text;
    private String pics;
    private Integer likes;
    @TableField(exist = false)
    private Boolean userLike;
    @TableField(value = "date")
    private Long createDate;
    @TableField(exist = false)
    private List<comment> commentList;

}
