package com.leis.bear.domain.vo;

import lombok.Data;

@Data
public class UserLoginVo {

    private String token;
    private String username;
    private Integer phone;
    private String icon;
    private String email;
    private String dept;
    private Byte ssex;
}
