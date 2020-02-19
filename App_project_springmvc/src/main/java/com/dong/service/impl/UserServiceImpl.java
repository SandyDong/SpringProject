package com.dong.service.impl;

import com.dong.dao.IUserDao;
import com.dong.entity.User;
import com.dong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @Resource   注解是按照名称查找bena对象进行注入。 属于j2ee注解
 * @Autowired  注解是按照类型查找bean对象注入 Spring框架自带注解
 *
 *
 * @Autowired是根据类型进行自动装配的。如果当Spring上下文中存在不止一个UserDao类型的bean时，就会抛出BeanCreationException异常;
 * 如果Spring上下文中不存在UserDao类型的bean，也会抛出BeanCreationException异常。我们可以使用@Qualifier配合@Autowired来解决这些问题。如下：
 * ①可能存在多个UserDao实例
 *  @Autowired
 *  @Qualifier("userServiceImpl")
 *
 *  或者
 *  @Autowired
 * public void setUserDao(@Qualifier("userDao") UserDao userDao) {
 *     this.userDao = userDao;
 * }
 *
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User getUserById(int id) {
        System.out.println("no message 1111");
       /* User user = new User();
        user.setName("wangqiang");
        user.setNumber(223333333);
        user.setAddress("北京市海淀区");*/
         return  userDao.getUserById(222);
    }

    public void insertUserInfo(User user) {
        userDao.insertUser(user);
    }

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
