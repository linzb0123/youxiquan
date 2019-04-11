package com.youxiquan.controller;


import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqInstantNews;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class InstantNewsController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(InstantNewsController.class);

    @Autowired
    private Services services;

    @RequestMapping("/instant-news-list")
    public String showPage(){
        return "instant-news-list";
    }

    @RequestMapping(value = "/instantNews/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getInstantNewsList(int draw, int start, int length, String searchKey,
                                               String minDate, String maxDate,
                                               @RequestParam("search[value]") String search,
                                               @RequestParam("order[0][column]") int orderCol,
                                               @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","user_name","game_name","message","create_time","end_time","reply_num","status"};
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
        DataTablesResult result=services.instantNewsService.getInstantNewsList(draw,start,length,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/instantNews/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getInstantNewsCount(){
        return services.instantNewsService.getInstantNewsCount();
    }

    @RequestMapping(value = "/instantNews/remove/{ids}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqInstantNews> deleteInstantNews(@PathVariable Long[] ids){

        for(Long id:ids){
            services.instantNewsService.removeInstantNews(id);
        }
        return new ResultUtil<YxqInstantNews>().setData(null);
    }

    /* 屏蔽 */
    @RequestMapping(value = "/instantNews/stop/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqInstantNews> stopInstantNews(@PathVariable Long id){
        YxqInstantNews yxqInstantNews =  services.instantNewsService.alertInstantNewsStatus(id,2);
        return new ResultUtil<YxqInstantNews>().setData(yxqInstantNews);
    }

    /* 启用 */
    @RequestMapping(value = "/instantNews/start/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result<YxqInstantNews> startInstantNews(@PathVariable Long id){
        YxqInstantNews instantNews = services.instantNewsService.getInstantNewsById(id);
        YxqInstantNews yxqInstantNews = null;
        if( instantNews.getEndTime().getTime() > new Date().getTime()){
            yxqInstantNews =  services.instantNewsService.alertInstantNewsStatus(id,0);
        }else {
            yxqInstantNews =  services.instantNewsService.alertInstantNewsStatus(id,1);
        }

        return new ResultUtil<YxqInstantNews>().setData(yxqInstantNews);
    }


    /* 获得帖子详情 */
    @RequestMapping(value = "/instantNews/getDetail/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result<InstantNewsAndReplyDto> getDetail(@PathVariable Long id){
        InstantNewsAndReplyDto instantNewsAndReplyDto = services.instantNewsService.getInstantNewsDetail(id);
        return new ResultUtil<InstantNewsAndReplyDto>().setData(instantNewsAndReplyDto);
    }
}
