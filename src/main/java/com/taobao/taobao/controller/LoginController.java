package com.taobao.taobao.controller;

import com.taobao.taobao.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  测试vue前后端分离项目
 * @ Author        :  wusi
 * @ CreateDate    :  2019/11/27$ 10:16$
 */
@RestController
@RequestMapping("abc")
public class LoginController {
    @RequestMapping("login")
    public Boolean login(@RequestBody User user){
        System.out.printf("用户名" + user.getUserName());
        System.out.printf("用户密码" + user.getPassword());
        return  true;
    }
}
