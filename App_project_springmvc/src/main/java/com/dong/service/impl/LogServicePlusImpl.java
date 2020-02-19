package com.dong.service.impl;

import com.dong.service.ILogServcie;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(0)
public class LogServicePlusImpl implements ILogServcie {

    public LogServicePlusImpl() {
        System.out.println("plus-service-加载!");
    }

    public String insertLogMessage(String str) {
        System.out.println("日志插入plus服务正在执行中!!!"+str);
        return "日志插入plus服务正在执行中!!!"+str;
    }
}
