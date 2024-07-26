package com.leis.bear.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class RoleMenu implements Serializable {
    private Integer menuId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;

}