package com.steveny.mapper;

import com.steveny.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    void register(@Param("name") String name, @Param("psw") String psw);

    List<User> loginUser(@Param("name") String name, @Param("psw") String psw);

    List<User> selectUserByName(@Param("name") String name);

    User selectUserByUuid(@Param("uuid") int uuid);
}
