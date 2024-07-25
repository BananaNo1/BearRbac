package com.leis.bear.domain;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    private String nickname;
    private String phone;
    private String icon;
    private String password;
    private String email;
    private String dept;
    private Integer status;
    private Integer sex;
    private Integer delFlag;

}
