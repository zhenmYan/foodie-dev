package com.imooc.service;

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

}
