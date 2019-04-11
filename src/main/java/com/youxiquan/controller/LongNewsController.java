package com.youxiquan.controller;


import com.youxiquan.dto.LongNewsAndReplyDto;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqLongNews;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LongNewsController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(LongNewsController.class);

    @Autowired
    private Services services;

    @RequestMapping(value = "/longNews/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getLongNewsList(int draw, int start, int length, String searchKey,
                                               String minDate, String maxDate,
                                               @RequestParam("search[value]") String search,
                                               @RequestParam("order[0][column]") int orderCol,
                                               @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","username","game_name","message","create_time","reply_num","praise_num","order_num","status"};
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
        DataTablesResult result=services.longNewsService.getLongNewsList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/longNews/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getLongNewsCount(){
        return services.longNewsService.getLongNewsCount();
    }

    @RequestMapping(value = "/longNews/remove/{ids}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNews> deleteYxqLongNews(@PathVariable Long[] ids){

        for(Long id:ids){
            services.longNewsService.removeLongNews(id);
        }
        return new ResultUtil<YxqLongNews>().setData(null);
    }

    /* 屏蔽 */
    @RequestMapping(value = "/longNews/stop/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNews> stopLongNews(@PathVariable Long id){
        YxqLongNews yxqLongNews =  services.longNewsService.alertLongNewsStatus(id,1);
        return new ResultUtil<YxqLongNews>().setData(yxqLongNews);
    }

    /* 启用 */
    @RequestMapping(value = "/longNews/start/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNews> startLongNews(@PathVariable Long id){
        YxqLongNews yxqLongNews =  services.longNewsService.alertLongNewsStatus(id,0);
        return new ResultUtil<YxqLongNews>().setData(yxqLongNews);
    }

    @RequestMapping(value = "/longNews/stick/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNews> stick(@PathVariable Long id){

        YxqLongNews yxqLongNews =  services.longNewsService.alertLongNewsOrder(id,0);
        return new ResultUtil<YxqLongNews>().setData(yxqLongNews);
    }

    /* 取消置顶默认是999 */
    @RequestMapping(value = "/longNews/unstick/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqLongNews> unstick(@PathVariable Long id){

        YxqLongNews yxqLongNews =  services.longNewsService.alertLongNewsOrder(id,999);
        return new ResultUtil<YxqLongNews>().setData(yxqLongNews);
    }

    /* 获得帖子详情 */
    @RequestMapping(value = "/longNews/getDetail/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result<LongNewsAndReplyDto> getDetail(@PathVariable Long id){
        LongNewsAndReplyDto longNewsAndReplyDto = services.longNewsService.getLongNewsDetail(id);
        return new ResultUtil<LongNewsAndReplyDto>().setData(longNewsAndReplyDto);
    }
}
