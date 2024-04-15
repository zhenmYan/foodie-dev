package com.imooc.controller;

import com.imooc.pojo.User;
import com.imooc.pojo.bo.UserBo;
import com.imooc.pojo.vo.UserVO;
import com.imooc.service.UserService;
import com.imooc.util.CookieUtils;
import com.imooc.util.JSONResult;
import com.imooc.util.JsonUtils;
import com.imooc.util.MD5Utils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * description:
 *
 * @author yzm
 * @date 2024/4/15  22:41
 */
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;


//    @GetMapping("/usernameExist")
//    public JSONResult userNameExist(@RequestParam String username) {
//        // 1. 判断用户名不能为空
//        if (StringUtils.isBlank(username)) {
//            return JSONResult.errorMsg("用户名不能为空");
//        }
//
//        // 2. 查找注册的用户名是否存在
//        boolean isExist = userService.queryUserNameExist(username);
//        if (isExist) {
//            return JSONResult.errorMsg("用户名已存在");
//        }
//        // 3. 用户名没有重复
//        return JSONResult.ok();
//    }

    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBo userBO,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        // 1. 查询用户名是否存在
        boolean isExist = userService.queryUserNameExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已经存在");
        }
        // 2. 密码长度不能少于 6 位
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能少于 6");
        }
        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return JSONResult.errorMsg("两次密码输入不一致");
        }
        // 4. 实现注册
        User user = userService.createUser(userBO);

        // 脱敏信息
        // setNullProperty(user);
        // 下面使用 userVo 之后，这个 脱敏信息的就不再需要了

//        UsersVO usersVO = convertVo(user);
//
//        // 设置 cookie,使用 userVO 返回
//        CookieUtils.setCookie(request, response, "user",
//                JsonUtils.objectToJson(usersVO), true);
//
//        // 同步购物车数据
//        synchShopcartData(user.getId(), request, response);
        return JSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBo userBO,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        password = MD5Utils.getMD5Str(password);
        User user = userService.queryUserForLogin(username, password);
        if (user == null) {
            return JSONResult.errorMsg("用户名或密码不正确");
        }

        // 脱敏信息
        // setNullProperty(user);

        // 设置 cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        // 生成用户 token 存入 redis 会话
        // 同步购物车数据
        return JSONResult.ok(user);
    }

//    public UserVO convertVo(User user) {
//        // 实现用户的 redis 会话
//        String uniqueToken = UUID.randomUUID().toString();
//        // 永远不过期，除非自动退出
//        // redis_user_token
//        redisOperator.set("redis_user_token" + ":" + user.getId(), uniqueToken);
//        UserVO usersVO = new UserVO();
//        BeanUtils.copyProperties(user, usersVO);
//        usersVO.setUniqueToken(uniqueToken);
//        return usersVO;
//    }
}
