package com.leis.bear.controller;


import com.leis.bear.domain.User;
import com.leis.bear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public List<User> selectUserList() {
        return userService.selectUserList();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        System.out.println(user);
        return null;
    }

}
