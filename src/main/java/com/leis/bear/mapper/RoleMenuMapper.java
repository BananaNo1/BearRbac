package com.leis.bear.mapper;

import com.leis.bear.domain.RoleMenu;
import java.util.List;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    List<RoleMenu> selectAll();
}