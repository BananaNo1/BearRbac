package com.leis.bear.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Menu extends BaseEntity implements Serializable {
    private Integer id;

    private String menuName;

    private Integer parentId;

    private String path;

    private String component;

    private String perms;

    private String icon;

    private Byte type;

    private Integer orderNum;

    private static final long serialVersionUID = 1L;

}