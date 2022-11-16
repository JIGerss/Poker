package com.steveny.service;

import com.steveny.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     *
     * @param name
     * @param psw
     * @return
     */
    List<User> loginUser(String name, String psw);

    /**
     * 根据uuid查询用户
     *
     * @param uuid
     * @return
     */
    User selectUserByUuid(int uuid);

    /**
     * 根据name查询用户
     *
     * @param name
     * @return
     */
    List<User> selectUserByName(String name);

    /**
     * 添加用户
     *
     * @param name
     * @param psw
     */
    void addUser(String name, String psw);
}
