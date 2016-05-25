package com.xsoft.appdemo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {

    @Resource
    private SqlSessionTemplate sqlSession;

    @Override
    public <T> int delete(String _mybitsId, T obj) {
        return sqlSession.delete(_mybitsId, obj);
    }

    @Override
    public <T> int insert(String _mybitsId, T obj) {
        return sqlSession.insert(_mybitsId, obj);
    }

    @Override
    public <T> int update(String _mybitsId, T obj) {
        return sqlSession.update(_mybitsId, obj);
    }

    @Override
    public <T> List<T> query(String _mybitsId, Map<String, Object> _params) {
        return sqlSession.selectList(_mybitsId, _params);
    }

    @Override
    public <T> List<T> query(String _mybitsId, Object _params) {
        return sqlSession.selectList(_mybitsId, _params);
    }

    @Override
    public Object queryOne(String _mybitsId, Object object) {
        return sqlSession.selectOne(_mybitsId, object);
    }
}