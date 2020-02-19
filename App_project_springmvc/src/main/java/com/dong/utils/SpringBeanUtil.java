package com.dong.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 通过该类可以直接获取Spring容器对象。并能进行操作Spring容器对象。
 *
 */
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         SpringBeanUtil.applicationContext = applicationContext;
    }

    /**
     * 获取指定名字的bean对象
     * @param name
     * @return
     */
    public static  Object  getBean(String name){
         return applicationContext.getBean(name);
    }

    /**
     * 这种获取的对象可以不用再强制转换
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T>  T  getBean(Class<T> clazz){
          return applicationContext.getBean(clazz);
    }
}
