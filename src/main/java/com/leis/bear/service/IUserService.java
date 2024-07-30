package com.leis.bear.service;

import com.leis.bear.domain.User;
import com.leis.bear.domain.vo.UserLoginVo;

import java.util.List;


public interface IUserService {

    List<User> selectUserList();

    boolean registerUser(User user);

    UserLoginVo loginUser(User user);
}
