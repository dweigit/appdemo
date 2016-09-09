package com.xsoft.appdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dwei on 2016/9/8.
 */
@Controller
public class IndexController {
    private static Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(Model model) throws Exception {
        return "index";
    }

    @RequestMapping("/main")
    public String test(Model model) throws Exception {
        return "main";
    }

    @RequestMapping("/page/{path}")
    public String demo(@PathVariable("path") String path) throws Exception {
        return path;
    }
}
