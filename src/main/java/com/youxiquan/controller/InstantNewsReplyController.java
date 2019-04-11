package com.youxiquan.controller;


import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqInstantNewsReply;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class InstantNewsReplyController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(InstantNewsReplyController.class);

    @Autowired
    private Services services;


    @RequestMapping(value = "/instantNewsReply/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getInstantNewsReplyList(int draw, int start, int length, String searchKey,
                                                    String minDate, String maxDate,
                                                    @RequestParam("search[value]") String search,
                                                    @RequestParam("order[0][column]") int orderCol,
                                                    @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","instant_id","username","message","create_time","status"};
        String orderColumn = cols[orderCol];

        //默认排序列
        if(orderColumn == null) {
            orderColumn = "create_time";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "asc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result=services.instantNewsReplyService.getInstantNewsReplyList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/instantNewsReply/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getInstantNewsReplyCount(){
        return services.instantNewsReplyService.getInstantNewsReplyCount();
    }

    @RequestMapping(value = "/instantNewsReply/remove/{ids}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqInstantNewsReply> deleteInstantNewsReply(@PathVariable Long[] ids){

        for(Long id:ids){
            services.instantNewsReplyService.removeInstantNewsReply(id);
        }
        return new ResultUtil<YxqInstantNewsReply>().setData(null);
    }

    /* 屏蔽 */
    @RequestMapping(value = "/instantNewsReply/stop/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqInstantNewsReply> stopInstantNewsReply(@PathVariable Long id){
        YxqInstantNewsReply YxqInstantReply =  services.instantNewsReplyService.alertInstantNewsReplyStatus(id,1);
        return new ResultUtil<YxqInstantNewsReply>().setData(YxqInstantReply);
    }

    /* 启用 */
    @RequestMapping(value = "/instantNewsReply/start/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqInstantNewsReply> startInstantNewsReply(@PathVariable Long id){
        YxqInstantNewsReply YxqInstantReply =  services.instantNewsReplyService.alertInstantNewsReplyStatus(id,0);
        return new ResultUtil<YxqInstantNewsReply>().setData(YxqInstantReply);
    }
}
