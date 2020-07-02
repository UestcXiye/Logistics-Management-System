package com.example.demo.controller;

//import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面跳转Controller
 */
//@Controller
public class PageController {

    /**
     * 页面跳转方法
     */
    @GetMapping("/{page}")
    public String showPage(@PathVariable String page){

        return page;
    }

}