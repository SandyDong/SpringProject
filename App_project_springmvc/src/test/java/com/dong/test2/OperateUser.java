package com.dong.test2;

public class OperateUser extends AbstractOperateUser {

    public void printInfo(Person person) {
        System.out.println("获取到person对象信息!");
    }

    public static void main(String[] args) {
        OperateUser operateUser = new OperateUser();
        Person  person = new Person();//子类对象
        User user = person;//父类引用指向子类对象 父类引用对象不能直接调用子类特有的方法。
//        User user1 = new Person();
        person.setName("dongwenqiang");
        person.setAddress("北京顺义区!");
        operateUser.printInfo(person);
        operateUser.getUserDetail(user);

    }
}
