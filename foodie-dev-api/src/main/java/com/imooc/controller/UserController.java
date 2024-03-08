package com.imooc.controller;

import com.imooc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/15  19:11
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/userExist")
    public int queryUserNameExist(@RequestParam String userName) {
        if (StringUtils.isBlank(userName)) {
            return 500;
        }
        boolean userExist = userService.queryUserNameExist(userName);
        if (userExist) {
            return 200;
        }
        return 500;
    }
}
