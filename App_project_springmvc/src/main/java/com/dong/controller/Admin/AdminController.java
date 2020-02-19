package com.dong.controller.Admin;

import com.dong.service.ISelectInfo;
import com.dong.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/info")
public class AdminController {

    @Resource(name = "selectInfo")
    private ISelectInfo iSelectInfo;

    @RequestMapping("/getMessage")
    public String getMessageInfo(){
        iSelectInfo.printMessage("sssss");
        //通过工具类获取Spring容器对象，进而获取容器类的对象.通过beanId获取bean对象
        ISelectInfo selectInfo = (ISelectInfo) SpringBeanUtil.getBean("selectInfo");
        selectInfo.printMessage("ss");
        ////通过工具类获取Spring容器对象，进而获取容器类的对象.通过beanId获取bean对象(直接获取，不用强制转换)
        ISelectInfo selectInfo1 =  SpringBeanUtil.getBean(ISelectInfo.class);
        selectInfo.printMessage("ssV");
        return "list";
    }
}
