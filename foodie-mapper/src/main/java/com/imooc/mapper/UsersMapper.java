package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper extends MyMapper<User> {

    /**
     * 根据用户姓名查询id
     *
     * @param userName
     * @return
     */
    User selectByName(@Param("userName")String userName);

}