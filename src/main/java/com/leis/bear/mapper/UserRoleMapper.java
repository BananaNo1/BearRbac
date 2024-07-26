package com.leis.bear.mapper;

import com.leis.bear.domain.UserRole;
import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();
}