package com.leis.bear.service;

import com.leis.bear.domain.User;

import java.util.List;


public interface IUserService {

    List<User> selectUserList();

    boolean registerUser(User user);

    Boolean loginUser(User user);
}
