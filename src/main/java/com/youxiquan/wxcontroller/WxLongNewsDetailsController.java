package com.youxiquan.wxcontroller;

import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.dto.LongNewsAndReplyDto;
import com.youxiquan.dto.LongNewsDto;
import com.youxiquan.pojo.YxqLongNews;
import com.youxiquan.pojo.YxqLongNewsReply;
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
@Api(description= "长期信息")
@RequestMapping("/api/longNews")
@RestController
public class WxLongNewsDetailsController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(WxLongNewsDetailsController.class);

    @Autowired
    private Services services;

    @ApiOperation(value="通过用户Id其关注的所有长期帖子的列表")
    @RequestMapping(value = "/getListByuserId/{userId}",method = RequestMethod.GET)
    public ResultInfo getListByuserId(@PathVariable Long userId){
        List<LongNewsDto> list = services.longNewsService.getList(userId);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="通过游戏Id该获得长期帖子的列表")
    @RequestMapping(value = "/getListByGameId/{gameId}",method = RequestMethod.GET)
    public ResultInfo getInstantNewsList(@PathVariable Long gameId){
        List<LongNewsDto> list = services.longNewsService.getListByGameId(gameId);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="获得长期帖子的详情")
    @RequestMapping(value = "/getDetails/{id}",method = RequestMethod.GET)
    public Result getDetails(@PathVariable Long id){
        LongNewsAndReplyDto longNewsAndReplyDto = services.longNewsService.getLongNewsDetail(id);
        return new ResultUtil<LongNewsAndReplyDto>().setData(longNewsAndReplyDto);
    }

    @ApiOperation(value="插入帖子评价")
    @RequestMapping(value = "/sendComment",method = RequestMethod.GET)
    public ResultInfo sendComment(@RequestParam("postId")long longId,
                                  @RequestParam("userId")long userId,
                                  @RequestParam("comment")String comment){
        YxqLongNewsReply yxqLongNewsReply = new YxqLongNewsReply();
        yxqLongNewsReply.setStatus(0);
        yxqLongNewsReply.setCreateTime(new Date());
        yxqLongNewsReply.setLongId(longId);
        yxqLongNewsReply.setMessage(comment);
        yxqLongNewsReply.setUserId(userId);
        YxqLongNews originalPost = services.longNewsService.getLongNewsById(longId);
        originalPost.setReplyNum(originalPost.getReplyNum()+1);
        originalPost.setOrderNum(originalPost.getOrderNum()-1);
        try{
            services.longNewsReplyService.insertLongNewsReply(yxqLongNewsReply);
            services.longNewsService.updateLongNews(originalPost.getId(),originalPost);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultInfo result = ResultInfo.getInstance("评论成功",200);
        return result;
    }

    @ApiOperation(value="通过用户Id获得自己发的长期帖子的列表")
    @RequestMapping(value = "/getMyselfByUserId/{userId}",method = RequestMethod.GET)
    public ResultInfo getMyselfByUserId(@PathVariable Long userId){
        List<LongNewsDto> list = services.longNewsService.getMyselfByUserId(userId);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="发帖（长期帖子)")
    @RequestMapping(value = "/sendPost",method = RequestMethod.GET)
    public ResultInfo sendPost(@RequestParam("userId")long userId,
                               @RequestParam("gameId")long gameId,
                               @RequestParam("message")String message) {
        YxqLongNews longNews = new YxqLongNews();
        longNews.setOrderNum(999);
        longNews.setStatus(0);
        longNews.setGameId(gameId);
        longNews.setCreateTime(new Date());
        longNews.setReplyNum(0);
        longNews.setMessage(message);
        longNews.setUserId(userId);
        longNews.setPraiseNum(0);
        if( services.longNewsService.insertLongNews(longNews) == 1 ) {
            ResultInfo result = ResultInfo.getInstance("发帖失败",400);
            return result;
        }else{
            ResultInfo result = ResultInfo.getInstance("成功",200);
            return result;
        }
    }

    @ApiOperation(value="删除帖子")
    @RequestMapping(value = "/deletePost",method = RequestMethod.POST)
    public ResultInfo sendPost(@RequestParam("id")long id){
        services.longNewsService.updatePostStatus(id,1);
        ResultInfo result = ResultInfo.getInstance("成功",200);
        return result;
    }
}
