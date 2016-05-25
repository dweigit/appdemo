package com.xsoft.appdemo.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xsoft.appdemo.dao.UserInfoDao;
import com.xsoft.appdemo.model.UserInfo;
import com.xsoft.appdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dwei on 2016/1/17.
 */

@Service
public class UserService implements IUserService {
    @Autowired
    UserInfoDao userInfoDao;

    public List<UserInfo> findAll() {
        return userInfoDao.selectAll();
    }

    public PageInfo<UserInfo> findByPage(int page) {
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(page, 10);
        List<UserInfo> list = userInfoDao.selectAll();
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
