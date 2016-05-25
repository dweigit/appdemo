package com.xsoft.appdemo.service;

import com.github.pagehelper.PageInfo;
import com.xsoft.appdemo.model.UserInfo;

import java.util.List;

public interface IUserService {
    List<UserInfo> findAll();
    PageInfo<UserInfo> findByPage(int page);
}