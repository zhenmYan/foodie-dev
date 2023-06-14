package com.imooc.service;

import com.imooc.pojo.Stu;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/14  21:28
 */
public interface StuService {

    Stu getStuInfo(int id);

    void saveStu(Stu stu);

    void updateStu(int id);

    void deleteStu(int id);
}
