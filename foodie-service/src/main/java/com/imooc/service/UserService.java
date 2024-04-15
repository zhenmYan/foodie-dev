package com.imooc.service;

import com.imooc.pojo.User;
import com.imooc.pojo.bo.UserBo;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/15  18:51
 */
public interface UserService {

    /**
     * 根据用户名查询用户是否存在
     * @param userName
     * @return
     */
    boolean queryUserNameExist(String userName);

    User createUser(UserBo userBo);

}
