package com.leis.bear.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role extends BaseEntity implements Serializable {
    private Integer roleId;

    private String roleName;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String remark;

    private static final long serialVersionUID = 1L;

}