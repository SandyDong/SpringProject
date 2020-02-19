package com.dong.service.impl;

import com.dong.service.ILogServcie;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Order定义的顺序和bean对象的加载顺序是没有关系的
 */
@Service
@Order(100)
public class LogServiceImpl implements ILogServcie {
    public LogServiceImpl() {
        System.out.println("正常service加载！---");
    }

    public String insertLogMessage(String str) {
        System.out.println("日志插入服务正在执行中!!!" + str);
        return "日志插入服务正在执行中!!!" + str;
    }
}
