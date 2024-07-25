package com.leis.bear.mapper;

import com.leis.bear.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectUserList();
}
