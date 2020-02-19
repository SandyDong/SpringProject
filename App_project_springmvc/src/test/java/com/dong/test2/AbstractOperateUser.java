package com.dong.test2;

/**
 * 定义一个抽象公共类(包含公共处理方法)
 */
public abstract class AbstractOperateUser implements  IScienceUser {

    public void  getUserDetail(User user){
        System.out.println("抽象类中获取user对象信息!");
    }

}
