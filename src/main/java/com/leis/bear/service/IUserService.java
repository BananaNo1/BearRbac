package com.leis.bear.service;

import com.leis.bear.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {

    List<User> selectUserList();
}
