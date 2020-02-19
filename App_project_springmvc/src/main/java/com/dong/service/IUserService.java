package com.dong.service;

import com.dong.entity.User;

public interface IUserService {

    public  User getUserById(int id);

    public  void insertUserInfo(User user);

    public  void  deleteUserById(int id);

    public  void  updateUser(User user);
}
