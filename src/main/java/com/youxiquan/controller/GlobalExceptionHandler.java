package com.youxiquan.controller;

import com.youxiquan.exception.MVCException;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.result.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lzb
 * @Description:统一异常处理
 * @Date: created in 15:18 2018/9/8
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = RestfulException.class)
    @ResponseBody
    public ResultInfo restfulExceptionHandler(HttpServletRequest req, RestfulException e) {
        return  ResultInfo.getInstance(e.getMsgDes(),ResultInfo.FAIL);
    }

//    @ExceptionHandler(value = MVCException.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        System.out.println("exception");
//        final String DEFAULT_ERROR_VIEW = "error500";
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }
}
