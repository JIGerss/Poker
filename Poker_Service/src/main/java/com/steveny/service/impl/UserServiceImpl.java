package com.steveny.service.impl;

import com.steveny.mapper.UserMapper;
import com.steveny.pojo.User;
import com.steveny.service.UserService;
import com.steveny.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    public List<User> loginUser(String name, String psw) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.loginUser(name, psw);
        sqlSession.close();
        return userList;
    }

    public User selectUserByUuid(int uuid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUuid(uuid);
        sqlSession.close();
        return user;
    }

    public List<User> selectUserByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectUserByName(name);
        sqlSession.close();
        return users;
    }

    public void addUser(String name, String psw) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.register(name, psw);
        sqlSession.commit();
        sqlSession.close();
    }
}
