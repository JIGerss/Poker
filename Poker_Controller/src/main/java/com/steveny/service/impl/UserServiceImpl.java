package com.steveny.service.impl;

import com.steveny.mapper.UserMapper;
import com.steveny.pojo.User;
import com.steveny.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private SqlSessionFactoryBean sqlSessionFactoryBean;

    public List<User> loginUser(String name, String psw) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactoryBean.getObject().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.loginUser(name, psw);
        sqlSession.close();
        return userList;
    }

    public User selectUserByUuid(int uuid) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactoryBean.getObject().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUuid(uuid);
        sqlSession.close();
        return user;
    }

    public List<User> selectUserByName(String name) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactoryBean.getObject().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUserByName(name);
        sqlSession.close();
        return users;
    }

    public void addUser(String name, String psw) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactoryBean.getObject().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.register(name, psw);
        sqlSession.commit();
        sqlSession.close();
    }
}
