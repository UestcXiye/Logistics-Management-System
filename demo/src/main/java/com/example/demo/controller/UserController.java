package com.example.demo.controller;

import com.example.demo.POJO.User;
import com.example.demo.dao.loginDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private loginDao LoginDao;

    @GetMapping("/login")
    public String login(User User){
        boolean success=LoginDao.lookUser(User);
        logger.info("用户登录");
        if(success==true){
            return "true";
        }
        return "false";
    }
}
