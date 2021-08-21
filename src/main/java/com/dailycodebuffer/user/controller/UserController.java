package com.dailycodebuffer.user.controller;

import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.service.UserService;
import com.dailycodebuffer.user.vo.UserWithDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @GetMapping("/{userId}")
    public UserWithDepartment getUserById(@PathVariable("userId") Long userId) {
        return userService.findUserById(userId);
    }

}
