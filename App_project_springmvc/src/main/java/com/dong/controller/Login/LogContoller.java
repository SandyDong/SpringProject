package com.dong.controller.Login;

import com.dong.entity.User;
import com.dong.service.ILogServcie;
import com.dong.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 日志处理控制器
 */

@RestController
@RequestMapping("/log")
public class LogContoller {

    private final static Logger logger = LoggerFactory.getLogger(LogContoller.class);

//    @Resource
//    private ILogServcie logServcie;


    @RequestMapping(name = "/insert",method = RequestMethod.GET)
    public String insertLogMessage(String logInfo) {

//        return logServcie.insertLogMessage(logInfo);
        return "success";
    }

}
