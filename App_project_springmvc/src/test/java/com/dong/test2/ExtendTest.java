package com.dong.test2;

/**
 * 类继承测试类
 */
public class ExtendTest {

    public static void main(String[] args) {
        System.out.println("aaaaa");
        User user = new Person();
        ((Person) user).setAddress("ssss");//需要强转对象，否则不能直接用父类引用对象调用子类特有方法
        user.setName("aaa");
        System.out.println(user.getName());
        System.out.println(((Person) user).getAddress());
    }
}
