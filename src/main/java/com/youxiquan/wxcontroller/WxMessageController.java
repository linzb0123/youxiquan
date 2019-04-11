package com.youxiquan.wxcontroller;

import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.pojo.YxqMessage;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import com.youxiquan.util.jedis.JConstants;
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
@Api(description= "朋友圈私信")
@RequestMapping("/api/message")
@RestController
public class WxMessageController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(WxMessageController.class);
    @Autowired
    private Services services;


    @ApiOperation(value="获得即时帖子的详情")
    @RequestMapping(value = "/getDetails/{id}",method = RequestMethod.GET)
    public Result getDetails(@PathVariable Long id){
        InstantNewsAndReplyDto instantNewsAndReplyDto = services.instantNewsService.getInstantNewsDetail(id);
        return new ResultUtil<InstantNewsAndReplyDto>().setData(instantNewsAndReplyDto);
    }


    @ApiOperation(value="根据时间戳获得消息")
    @RequestMapping(value = "/getMessage/{timestamp}",method = RequestMethod.GET)
    public ResultInfo getMessagesList(@RequestHeader("token") String token,@PathVariable long timestamp){
        String userId = services.jedisClient.hget(token, JConstants.USERID);
        if(timestamp==0){
            timestamp = System.currentTimeMillis()-60*60*24*15*1000;//只保存15天的消息
        }
        List<YxqMessage> messageList = services.messageService.getMessageByDate(new Long(userId),new Date(timestamp));
        return  ResultInfo.success("success","messages",messageList);
    }



}
