package com.leis.bear.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.leis.bear.domain.User;
import com.leis.bear.domain.vo.UserLoginVo;
import com.leis.bear.exception.BadRequestException;
import com.leis.bear.exception.UnAuthorizedException;
import com.leis.bear.mapper.UserMapper;
import com.leis.bear.service.IUserService;
import com.leis.bear.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

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
    public UserLoginVo loginUser(User user) {
        if (StrUtil.isBlank(user.getUsername())) {
            throw new BadRequestException("用户名错误");
        }
        User usernameUser = userMapper.selectAllByUsernameUser(user.getUsername());
        if (usernameUser == null) {
            throw new UnAuthorizedException("用户名或者密码错误");
        }
        if (!MD5.create().digestHex16(user.getPassword()).equals(usernameUser.getPassword())) {
            throw new UnAuthorizedException("用户名或者密码错误");
        }
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtil.copyProperties(usernameUser, userLoginVo);
        String token = jwtUtil.createToken(usernameUser.getId(), Duration.ofMinutes(10));
        userLoginVo.setToken(token);
        return userLoginVo;
    }
}
