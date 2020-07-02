package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class GlobalException implements HandlerExceptionResolver {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 全局异常处理类
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView mv =new ModelAndView();
        if(e instanceof NullPointerException){
            mv.setViewName("nullPtrError");
            logger.error("发生一次空指针异常");
        }else if(e instanceof ArithmeticException) {
            mv.setViewName("arithError");
            logger.error("发生一次运算异常");
        }else{
            mv.setViewName("error");
            logger.error("发生一次其他异常");
        }
        mv.addObject("error",e.toString());
        return mv;
    }
}