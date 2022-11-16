package com.steveny.service.impl;

import com.steveny.mapper.UserMapper;
import com.steveny.pojo.User;
import com.steveny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> loginUser(String name, String psw) {
        return userMapper.loginUser(name, psw);
    }

    public User selectUserByUuid(int uuid) {
        return userMapper.selectUserByUuid(uuid);
    }

    public List<User> selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    public void addUser(String name, String psw) {
        userMapper.register(name, psw);
    }

}
