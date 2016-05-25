package com.xsoft.appdemo.controller;

import com.github.pagehelper.PageInfo;
import com.xsoft.appdemo.model.UserInfo;
import com.xsoft.appdemo.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;

    @RequestMapping("/list_json")
    @ResponseBody
    public Object listJson(HttpServletRequest request, HttpServletResponse response) {
        List<UserInfo> list = this.userService.findAll();
        log.info("list->{}", list);
        return list;
    }

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        List<UserInfo> list = this.userService.findAll();
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("userList", list);
        return mav;
    }

    @RequestMapping("/list_page")
    public ModelAndView listPage(HttpServletRequest request, HttpServletResponse response) {
        long l1 = System.currentTimeMillis();
        int n = StringUtils.isBlank(request.getParameter("page"))?1:Integer.parseInt(request.getParameter("page"));
        PageInfo<UserInfo> pageInfo = this.userService.findByPage(n);
        ModelAndView mav=new ModelAndView("list_page");
        mav.addObject("pageInfo",pageInfo);
        long l2 = System.currentTimeMillis();
        log.info("total time use: " + (l2-l1));
        return mav;
    }


}