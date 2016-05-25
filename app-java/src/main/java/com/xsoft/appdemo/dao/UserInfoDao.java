package com.xsoft.appdemo.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.xsoft.appdemo.jetty.COOASProperties;
import com.xsoft.appdemo.model.UserInfo;
import com.xsoft.appdemo.util.DatabaseSqliteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface UserInfoDao {
    public int insert(UserInfo user);

    public int update(UserInfo user);

    public int delete(String userName);

    public List<UserInfo> selectAll();

    public int countAll();

    public UserInfo findByUserName(String userName);
}