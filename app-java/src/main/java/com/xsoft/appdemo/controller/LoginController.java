package com.xsoft.appdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dwei on 2016/9/8.
 */
@Controller
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    /**
     * 退出
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(Model model) {
        return "login";
    }

    /**
     * 注册
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model) {
        return "/register";
    }

    @RequestMapping("/table")
    public String table(Model model) {
        return "/table";
    }

    @RequestMapping("/table2")
    public String table2(Model model) {
        return "/table2";
    }
}
