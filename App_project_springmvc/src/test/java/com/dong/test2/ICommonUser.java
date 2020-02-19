package com.dong.test2;

/**
 * 定义一个泛型父类接口(公共)
 */
public interface ICommonUser<T extends User> {

    void  printInfo(T t);

}
