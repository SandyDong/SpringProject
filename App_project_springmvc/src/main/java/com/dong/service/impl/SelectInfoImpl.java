package com.dong.service.impl;

import com.dong.service.ISelectInfo;
import org.springframework.stereotype.Service;

@Service("selectInfo")
public class SelectInfoImpl implements ISelectInfo {

    public void printMessage(String message) {
        System.out.println("打印出日志信息"+message);
    }
}
