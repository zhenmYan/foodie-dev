package com.imooc.controller;

import com.imooc.service.StuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/14  21:33
 */
@RestController
public class StuController {

    @Resource
    private StuService stuService;

    @GetMapping("/getStu")
    public Object getStu(int id) {
        return stuService.getStuInfo(id);
    }
}

