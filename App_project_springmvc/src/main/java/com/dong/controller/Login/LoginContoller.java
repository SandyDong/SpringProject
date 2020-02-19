package com.dong.controller.Login;

import com.dong.entity.User;
import com.dong.entity.UserDTO;
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

@Controller
@RequestMapping("/login")
public class LoginContoller {

     private final static Logger logger = LoggerFactory.getLogger(LoginContoller.class);
   /* @Resource(name ="userService")   注入bean对象 方法一
    private IUserService userService;*/

   /* @Autowired
    @Qualifier("userService")
    private IUserService userService; */  //注入bean对象 方法二


    private  IUserService  userService;  //注入bean对象  方法三

    @Autowired
    public void setUserService(@Qualifier("userService") IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getInfo")
    public String getMessage(String username,String password){
//        System.out.println(username+"  "+password);
//        System.out.println("访问成功！");
        /*if (logger.isDebugEnabled()){
            logger.debug("debug访问成功!");
        }else if (logger.isInfoEnabled()){
            logger.info("info访问成功!");
        }else if (logger.isErrorEnabled()){
            logger.error("error访问成功!");
        }else if (logger.isWarnEnabled()){
            logger.warn("warn[test]访问成功!");
        }*/

        //TRACE < DEBUG < INFO < WARN < ERROR
        logger.error("----error访问成功!");
        logger.warn("-----warn访问成功!");
        logger.info("-----info访问成功!");
        logger.debug("-----debug访问成功!");
        logger.trace("-----trace访问成功!");

        ModelAndView   modelAndView = new ModelAndView();
        User user  =  new User();
        /*user.setName("wang");
        user.setAddress("beijing");
        user.setNumber(112);*/
        modelAndView.addObject("user",user);
        return "list";
    }

    @RequestMapping("/getUserInfo")
    public String  getUserInfo(){
       Integer id = 222;
       userService.getUserById(id);
      System.out.println("正在请求中!");
      logger.info("正在请求中!");
       return "success";
    }

    @RequestMapping("/addUserInfo")
    public String insertUserInfo(Model model){
        User user = new User();
         user.setName("test");
         user.setAge("19");
         user.setId(109);
         user.setSex("男");
         userService.insertUserInfo(user);
         model.addAttribute("userName",user.getName());
       return "add";
    }
     @RequestMapping("/deleteUserInfo")
    public String deleteUserById(Model model){
        int  id = 109;
        User user = userService.getUserById(id);
        model.addAttribute("userName",user.getName());
        userService.deleteUserById(id);
        return "delete";
    }
     @RequestMapping("/updateUserInfo")
    public String updateUser(Model model){
        //通过id修改用户信息
        User user = new User();
        user.setName("test123");
        user.setAge("22");
        user.setId(109);
        user.setSex("男");
        model.addAttribute("userName",user.getName());
        return "update";
    }
    @RequestMapping(value="/selectInfo")
    public ModelAndView  selectInfo(){
        ModelAndView modelAndView = new ModelAndView("list");
        return  modelAndView;
    }
}
