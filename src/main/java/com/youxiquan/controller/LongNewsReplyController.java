package com.youxiquan.controller;


import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqInstantNewsReply;
import com.youxiquan.pojo.YxqLongNewsReply;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LongNewsReplyController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(LongNewsReplyController.class);

    @Autowired
    private Services services;


    @RequestMapping(value = "/longNewsReply/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getLongNewsReplyList(int draw, int start, int length, String searchKey,
                                                    String minDate, String maxDate,
                                                    @RequestParam("search[value]") String search,
                                                    @RequestParam("order[0][column]") int orderCol,
                                                    @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","long_id","username","message","create_time","status"};
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
        DataTablesResult result=services.longNewsReplyService.getLongNewsReplyList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/longNewsReply/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getLongNewsReplyCount(){
        return services.longNewsReplyService.getLongNewsReplyCount();
    }

    @RequestMapping(value = "/longNewsReply/remove/{ids}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNewsReply> deleteLongNewsReply(@PathVariable Long[] ids){

        for(Long id:ids){
            services.longNewsReplyService.removeLongNewsReply(id);
        }
        return new ResultUtil<YxqLongNewsReply>().setData(null);
    }

    /* 屏蔽 */
    @RequestMapping(value = "/longNewsReply/stop/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNewsReply> stopLongNewsReply(@PathVariable Long id){
        YxqLongNewsReply YxqLongReply =  services.longNewsReplyService.alertLongNewsReplyStatus(id,1);
        return new ResultUtil<YxqLongNewsReply>().setData(YxqLongReply);
    }

    /* 启用 */
    @RequestMapping(value = "/longNewsReply/start/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNewsReply> startLongNewsReply(@PathVariable Long id){
        YxqLongNewsReply YxqLongReply =  services.longNewsReplyService.alertLongNewsReplyStatus(id,0);
        return new ResultUtil<YxqLongNewsReply>().setData(YxqLongReply);
    }
}
