package com.leis.bear.mapper;

import com.leis.bear.domain.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByUserId(Integer userId);
}