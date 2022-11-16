package com.steveny.mapper;

import com.steveny.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into users(name, psw) value (#{name}, #{psw})")
    void register(@Param("name") String name, @Param("psw") String psw);

    @Select("select * from users where name = #{name} and psw = #{psw}")
    List<User> loginUser(@Param("name") String name, @Param("psw") String psw);

    @Select("select * from users where uuid = #{uuid}")
    List<User> selectUserByName(@Param("name") String name);

    @Select("select * from users where name = #{name}")
    User selectUserByUuid(@Param("uuid") int uuid);
}
