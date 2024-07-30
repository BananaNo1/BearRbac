package com.leis.bear.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User extends BaseEntity implements Serializable {
    private Long id;

    private String username;

    private Integer phone;

    private String icon;

    private String password;

    private String email;

    private String dept;

    private Byte status;

    private Byte ssex;

    private String userType;

    private Byte delFlag;

    private String remark;

    private static final long serialVersionUID = 1L;

}