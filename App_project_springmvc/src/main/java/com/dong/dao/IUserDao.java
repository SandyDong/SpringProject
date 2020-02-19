package com.dong.dao;

import com.dong.controller.Login.LoginContoller;
import com.dong.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 创建DAO操作接口类
 */

//加上该注解才能使用@MapperScan扫描到
//@MapperScan
public interface IUserDao {

    User getUserById(@Param("id") int id);

    int updateUser(@Param("user") User user);

    int insertUser(@Param("user") User user);

    int deleteUserById(@Param("id") int id);

}
