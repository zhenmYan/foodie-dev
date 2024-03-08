package com.imooc.service.impl;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.User;
import com.imooc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/15  18:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public boolean queryUserNameExist(String userName) {

        User user = usersMapper.selectByName(userName);
        return !Objects.equals(null,user);
    }
}
