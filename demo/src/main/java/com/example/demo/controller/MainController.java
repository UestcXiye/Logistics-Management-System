package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主控制层，返回物流管理系统登陆界面
 */

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping()
    public String main(){
        logger.info("用户进入登陆界面");
        return "login";
    }
}
