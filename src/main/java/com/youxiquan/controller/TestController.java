package com.youxiquan.controller;

import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    final static org.slf4j.Logger log= LoggerFactory.getLogger(TestController.class);


    @Autowired
    private Services services;

    @RequestMapping("/test2")
    public String tes2t(){
        return "index";
    }
}
