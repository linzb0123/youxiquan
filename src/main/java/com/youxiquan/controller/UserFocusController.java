package com.youxiquan.controller;

import com.youxiquan.pojo.Admin;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.pojo.YxqUserFocus;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class UserFocusController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(UserFocusController.class);

    @Autowired
    private Services services;



    @RequestMapping(value = "/userFocus/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserList(int draw, int start, int length, String searchKey,
                                            @RequestParam("search[value]") String search,
                                            @RequestParam("order[0][column]") int orderCol,
                                            @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","username","gameName"};
        String orderColumn = cols[orderCol];

        //默认排序列
        if(orderColumn == null) {
            orderColumn = "gameName";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "asc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result=services.userFocusService.getUserFocusList(draw,start,length,searchKey,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/userFocus/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserFoucsCount()
    {
        return services.userFocusService.getUserFocusCount();
    }

    @RequestMapping(value = "/userFocus/remove/{ids}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqUserFocus> deleteYxqUserFoucs(@PathVariable Long[] ids){

        for(Long id:ids){
            services.userFocusService.removeUserFocus(id);
        }
        return new ResultUtil<YxqUserFocus>().setData(null);
    }

}
