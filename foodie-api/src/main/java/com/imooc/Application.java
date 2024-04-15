package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * description:
 *
 * @author 颜真明
 * @date 2023/6/13  17:17
 */
@SpringBootApplication(
        scanBasePackages = {"com.imooc", "idworker"},
        exclude = {SecurityAutoConfiguration.class}
)
@MapperScan(basePackages = "com.imooc.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
