package com.youxiquan.controller;

import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqUserFocus;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserConcernsController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(UserConcernsController.class);

    @Autowired
    private Services services;



    @RequestMapping(value = "/userConcerns/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserConcernsList(int draw, int start, int length, String searchKey,
                                                @RequestParam("search[value]") String search,
                                                @RequestParam("order[0][column]") int orderCol,
                                                @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"id","a.from_id","a.to_id","a.createTime"};
        String orderColumn = cols[orderCol];

        //默认排序列
        if(orderColumn == null) {
            orderColumn = "createtime";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "asc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result = services.userConcornsService.getUserConcernsList(draw,start,length,searchKey,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/userConcerns/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserConcernsCount()
    {
        return services.userConcornsService.getUserConcernsCount();
    }

}
