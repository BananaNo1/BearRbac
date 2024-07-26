package com.leis.bear.mapper;

import com.leis.bear.domain.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> selectByUserId(Integer userId);
}