package com.leis.bear.controller;


import com.leis.bear.domain.User;
import com.leis.bear.domain.vo.UserLoginVo;
import com.leis.bear.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "用户列表")
    @GetMapping("/list")
    public List<User> selectUserList() {
        return userService.selectUserList();
    }

    @Operation(summary = "注册用户")
    @PostMapping("/register")
    public Boolean registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Boolean loginUser(@RequestBody User user) {
        UserLoginVo userLoginVo = userService.loginUser(user);
        log.info(userLoginVo.toString());
        return true;
    }
}
