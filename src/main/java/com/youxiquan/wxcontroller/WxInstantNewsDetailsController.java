package com.youxiquan.wxcontroller;

import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.dto.InstantNewsDto;
import com.youxiquan.pojo.YxqInstantNews;
import com.youxiquan.pojo.YxqInstantNewsReply;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * @author yzx
 * 2018/12/19
 */
@Api(description= "即时信息")
@RequestMapping("/api/instantNews")
@RestController
public class WxInstantNewsDetailsController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(WxInstantNewsDetailsController.class);

    @Autowired
    private Services services;


    @ApiOperation(value="通过用户Id其关注的所有即时帖子的列表")
    @RequestMapping(value = "/getListByuserId/{userId}",method = RequestMethod.GET)
    public ResultInfo getListByuserId(@PathVariable Long userId){
        List<InstantNewsDto> list = services.instantNewsService.getList(userId);
        for(InstantNewsDto instantNews:list){
            if(instantNews.getEndTime().getTime()<new Date().getTime()){
                instantNews.setStatus(1);
                services.instantNewsService.alertInstantNewsStatus(instantNews.getId(),1);
            }
        }
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="通过游戏Id该获得即时帖子的列表")
    @RequestMapping(value = "/getListByGameId/{gameId}",method = RequestMethod.GET)
    public ResultInfo getInstantNewsList(@PathVariable Long gameId){
        List<InstantNewsDto> list = services.instantNewsService.getListByGameId(gameId);
        for(InstantNewsDto instantNews:list){
            if(instantNews.getEndTime().getTime()<new Date().getTime()){
                instantNews.setStatus(1);
                services.instantNewsService.alertInstantNewsStatus(instantNews.getId(),1);
            }
        }
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="获得即时帖子的详情")
    @RequestMapping(value = "/getDetails/{id}",method = RequestMethod.GET)
    public Result getDetails(@PathVariable Long id){
        InstantNewsAndReplyDto instantNewsAndReplyDto = services.instantNewsService.getInstantNewsDetail(id);
        return new ResultUtil<InstantNewsAndReplyDto>().setData(instantNewsAndReplyDto);
    }

    @ApiOperation(value="插入帖子评价")
    @RequestMapping(value = "/sendComment",method = RequestMethod.GET)
    public ResultInfo sendComment(@RequestParam("postId")long instantId,
                                  @RequestParam("userId")long userId,
                                  @RequestParam("comment")String comment){
        YxqInstantNews yxqInstantNews = services.instantNewsService.getInstantNewsById(instantId);
        if(yxqInstantNews.getEndTime().getTime()<new Date().getTime()){
            services.instantNewsService.alertInstantNewsStatus(instantId,1);
            ResultInfo result = ResultInfo.getInstance("帖子已过期",400);
            return result;
        }else{
            YxqInstantNewsReply yxqInstantNewsReply = new YxqInstantNewsReply();
            yxqInstantNewsReply.setCreateTime(new Date());
            yxqInstantNewsReply.setStatus(0);
            yxqInstantNewsReply.setInstantId(instantId);
            yxqInstantNewsReply.setMessage(comment);
            yxqInstantNewsReply.setUserId(userId);
            //原帖子的评论加一
            YxqInstantNews originalPost = services.instantNewsService.getInstantNewsById(instantId);
            originalPost.setReplyNum(originalPost.getReplyNum()+1);
            try{
                services.instantNewsReplyService.insertInstantNewsReply(yxqInstantNewsReply);
                services.instantNewsService.updateInstantNews(originalPost.getId(),originalPost);
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultInfo result = ResultInfo.getInstance("评论成功",200);
            return result;
        }

    }

    @ApiOperation(value="通过用户Id其自己发的即时帖子的列表")
    @RequestMapping(value = "/getMyselfByUserId/{userId}",method = RequestMethod.GET)
    public ResultInfo getMyselfByUserId(@PathVariable Long userId){
        List<InstantNewsDto> list = services.instantNewsService.getMyselfByUserId(userId);
        for(InstantNewsDto instantNews:list){
            if(instantNews.getEndTime().getTime()<new Date().getTime() && instantNews.getStatus() != 1 ){
                instantNews.setStatus(1);
                services.instantNewsService.alertInstantNewsStatus(instantNews.getId(),1);
            }
        }
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="发帖（即刻帖子)")
    @RequestMapping(value = "/sendPost",method = RequestMethod.GET)
    public ResultInfo sendPost(@RequestParam("userId")long userId,
                               @RequestParam("hour")int hour,
                               @RequestParam("minute")int minute,
                               @RequestParam("gameId")long gameId,
                               @RequestParam("message")String message) {
        long time = (hour * 3600 + minute * 10 * 60) * 1000;
        Date endTime = new Date(new Date().getTime()+time);
        YxqInstantNews instantNews = new YxqInstantNews();
        instantNews.setStatus(0);
        instantNews.setCreateTime(new Date());
        instantNews.setUserId(userId);
        instantNews.setEndTime(endTime);
        instantNews.setGameId(gameId);
        instantNews.setReplyNum(0);
        instantNews.setMessage(message);
        if( services.instantNewsService.insertInstantsNews(instantNews) == 1 ){
            ResultInfo result = ResultInfo.getInstance("发帖失败",400);
            return result;
        }else{
            ResultInfo result = ResultInfo.getInstance("成功",200);
            return result;
        }
    }

    @ApiOperation(value="删除帖子")
    @RequestMapping(value = "/deletePost",method = RequestMethod.POST)
    public ResultInfo deletePost(@RequestParam("id")long id){
        services.instantNewsService.alertInstantNewsStatus(id,2);
        ResultInfo result = ResultInfo.getInstance("成功",200);
        return result;
    }

}
