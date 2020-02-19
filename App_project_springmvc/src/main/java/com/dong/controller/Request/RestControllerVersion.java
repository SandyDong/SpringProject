package com.dong.controller.Request;

import com.dong.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerVersion {

    @RequestMapping(value = "/getUserMessage",method = RequestMethod.GET,produces = "application/json", consumes = "application/json")
    public User getUserINfo(){
      User user = new User();
      user.setName("test");
      user.setAge("11");
      user.setId(1002);
      user.setSex("男");
      return user;
    }
}
