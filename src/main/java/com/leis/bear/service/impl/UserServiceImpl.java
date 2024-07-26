package com.leis.bear.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
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
        return userMapper.selectAll();
    }

    @Override
    public boolean registerUser(User user) {
        String newPwd = MD5.create().digestHex16(user.getPassword());
        user.setPassword(newPwd);
        user.setUserType("00");
        user.setCreateUser("admin");
        user.setUpdateUser("admin");
        user.setCreateTime(DateUtil.date());
        user.setUpdateTime(DateUtil.date());
        int insert = userMapper.insert(user);
        return insert > 0;
    }

    @Override
    public Boolean loginUser(User user) {
        if (StrUtil.isBlank(user.getUsername())) {
            return false;
        }
        User usernameUser = userMapper.selectAllByUsernameUser(user.getUsername());
        if (usernameUser == null) {
            return false;
        }
        if (!MD5.create().digestHex16(user.getPassword()).equals(usernameUser.getPassword())) {
            return false;
        }
        return true;
    }
}
