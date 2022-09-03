package com.nart.vo;

import com.nart.pojo.User;
import lombok.Data;

@Data
public class UserVo {
    private String id;
    private String avatar;
    private String uname;
    private String pwd;
    private String email;
    private String birthday;
    private String phone;
    private String address;

    public UserVo transfer(User user){

    }
}