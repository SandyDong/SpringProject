package com.dong.controller.Login;

import com.dong.entity.ApplicationEmail;
import com.dong.entity.Login;
import com.dong.entity.User;
import com.dong.entity.UserDTO;
import com.dong.service.IUserService;
import com.dong.service.common.ApplicationMailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class LoginContoller {

    private final static Logger logger = LoggerFactory.getLogger(LoginContoller.class);
   /* @Resource(name ="userService")   注入bean对象 方法一
    private IUserService userService;*/

   /* @Autowired
    @Qualifier("userService")
    private IUserService userService; */  //注入bean对象 方法二

    private IUserService userService;  //注入bean对象  方法三

    @Autowired
    public void setUserService(@Qualifier("userService") IUserService userService) {
        this.userService = userService;
    }

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ApplicationMailer applicationMailer;

    //    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @GetMapping(value = "/login")
    public ModelAndView loginByNameAndPw(String username, String password)throws Exception {

        /*********获取请求信息start************/
        System.out.println(request.getRequestURI());
        System.out.println(request.getRemoteUser());
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String contentType = request.getContentType();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        /*********获取请求信息end************/

        Login login = new Login();
        login.setName(username);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
        String formatDate = simpleDateFormat.format(new Date());
        login.setDate(formatDate);
        login.setContentType(contentType);
        login.setRemoteIp(remoteHost);
        login.setRemotePort(remotePort);
        login.setServerName(serverName);
        login.setServerPort(serverPort);
        login.setRequestUrl(request.getRequestURI());

        //登录邮件发送监控
        ApplicationEmail applicationEmail = new ApplicationEmail();
        applicationEmail.setSubject("系统登录提醒!");
        applicationEmail.setContent("用户["+username+"]于"+formatDate+"访问了系统!");
        applicationEmail.setAddressee("343636059@qq.com");

        try {
            //同步发送邮件
//            applicationMailer.sendMailBySynchronizationMode(applicationEmail);
            //异步发送邮件
            applicationMailer.sendMailByAsynchronousMode(applicationEmail);
        }catch (Exception e){
            logger.error("邮件发送失败!");
            throw new Exception("邮件发送失败!");
        }

        return new ModelAndView("list", "login", login);
    }

    @RequestMapping(value = "/loginByUser", method = RequestMethod.POST)
    public ModelAndView getMessage(@RequestBody @Valid User user) {

        /*********获取请求信息start************/
        System.out.println(request.getRequestURI());
        System.out.println(request.getRemoteUser());
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String contentType = request.getContentType();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        /*********获取请求信息end**************/

        Login login = new Login();
        login.setName(user.getName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
        login.setDate(simpleDateFormat.format(new Date()));
        login.setContentType(contentType);
        login.setRemoteIp(remoteHost);
        login.setRemotePort(remotePort);
        login.setServerName(serverName);
        login.setServerPort(serverPort);
        login.setRequestUrl(request.getRequestURI());

        return new ModelAndView("list", "login", login);
    }

    @RequestMapping("/getUserInfo")
    public String getUserInfo() {
        Integer id = 222;
        userService.getUserById(id);
        System.out.println("正在请求中!");
        logger.info("正在请求中!");
        return "success";
    }

    @RequestMapping("/addUserInfo")
    public String insertUserInfo(Model model) {
        User user = new User();
        user.setName("test");
         /*user.setAge("19");
         user.setId(109);
         user.setSex("男");*/
        userService.insertUserInfo(user);
        model.addAttribute("userName", user.getName());
        return "add";
    }


    @RequestMapping("/deleteUserInfo")
    public String deleteUserById(Model model) {
        int id = 109;
        User user = userService.getUserById(id);
        model.addAttribute("userName", user.getName());
        userService.deleteUserById(id);
        return "delete";
    }


    @RequestMapping("/updateUserInfo")
    public String updateUser(Model model) {
        //通过id修改用户信息
        User user = new User();
        user.setName("test123");
       /* user.setAge("22");
        user.setId(109);
        user.setSex("男");*/
        model.addAttribute("userName", user.getName());
        return "update";
    }


    @RequestMapping(value = "/selectInfo")
    public ModelAndView selectInfo() {
        ModelAndView modelAndView = new ModelAndView("list");
        return modelAndView;
    }
}
