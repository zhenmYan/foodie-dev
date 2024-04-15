package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.User;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.UserService;
import com.imooc.util.DateUtil;
import com.imooc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/15  18:52
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Resource
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public boolean queryUserNameExist(String username) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);

        User result = usersMapper.selectOneByExample(example);
        return null != result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User createUser(UserBo userBO) {
        User users = new User();
        users.setUsername(userBO.getUsername());
        try {
            // 使用自定义工具类对密码进行 MD5 加密
            users.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用 ID 生成器生成 ID
//        users.setId(sid.nextShort());
        // 默认同用户昵称一样
        users.setNickname(userBO.getUsername());
        // 设置用户默认头像
        users.setFace(USER_FACE);
        // 默认生日为：1900-01-01
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 使用 枚举代替数字类型
        users.setSex(Sex.SECRET.getType());
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);
        // 返回用户是为了方便前端展示一些信息
        return users;
    }
//
//    @Transactional(propagation = Propagation.SUPPORTS)
//    @Override
//    public Users queryUserForLogin(String username, String passwod) {
//        Example example = new Example(Users.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("username", username);
//        criteria.andEqualTo("password", passwod);
//
//        Users result = usersMapper.selectOneByExample(example);
//        return result;
//    }

}
