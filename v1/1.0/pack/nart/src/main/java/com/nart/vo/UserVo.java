package com.nart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String id;
    private String avatar;
    private String uname;
    private String pwd;
    private String email;
    private String birthday;
    private String phone;
    private String address;
}
