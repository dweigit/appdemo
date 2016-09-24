package com.xsoft.appdemo.controller;

import com.xsoft.appdemo.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/ajax/json")
    @ResponseBody
    public Object listJson() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","jack");
        map.put("age","28");
        map.put("mobile","18900001001");
        return map;
    }

    @RequestMapping("/page/{path}")
    public String demo(@PathVariable("path") String path) throws Exception {
        return path;
    }
}
