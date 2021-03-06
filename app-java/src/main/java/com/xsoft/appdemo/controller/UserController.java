package com.xsoft.appdemo.controller;

import com.github.pagehelper.PageInfo;
import com.xsoft.appdemo.ext.xssfilter.XSSRequestWrapper;
import com.xsoft.appdemo.model.UserInfo;
import com.xsoft.appdemo.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.owasp.esapi.ESAPI;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    @RequestMapping("/list1")
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

    @RequestMapping("/xss_filter")
    @ResponseBody
    public Object xssFilter(HttpServletRequest request, HttpServletResponse response) {
        String val = request.getParameter("content");
        log.info("-------->"+val);
//        String safe = ESAPI.encoder().encodeForHTML(val);
        String safe = stripXSS(val);
        log.info("-------->"+safe);
        Map map = new HashMap();
        map.put("result",safe);
        return map;
    }

    @RequestMapping("/xss_test")
    public ModelAndView xssTest(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav=new ModelAndView("xss_test");
        return mav;
    }

    public String stripXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            //对用户输入“input”进行HTML编码，防止XSS。
//            String safe = ESAPI.encoder().encodeForHTML(value);


            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

        }
        return value;
    }
}