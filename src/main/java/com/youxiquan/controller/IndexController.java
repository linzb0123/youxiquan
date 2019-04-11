package com.youxiquan.controller;

import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    final static org.slf4j.Logger log= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private Services services;


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/test")
    @ResponseBody
    public ResultInfo test(){
        return ResultInfo.getInstance("haha",200);
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }


}
