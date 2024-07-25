package com.leis.bear.service.impl;

import com.leis.bear.domain.User;
import com.leis.bear.mapper.UserMapper;
import com.leis.bear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }
}
