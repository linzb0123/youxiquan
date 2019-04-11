package com.youxiquan.wxcontroller;

import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.dto.*;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqInstantNewsReply;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.pojo.YxqUserFocus;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.ArrayStack;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author yzx
 * 2018/12/21
 */
@Api(description= "用户信息")
@RequestMapping("/api/userInfo")
@RestController
public class WxUserInfoController {
    final static org.slf4j.Logger log= LoggerFactory.getLogger(WxUserInfoController.class);

    @Autowired
    private Services services;

    @ApiOperation(value="通过用户Id获得用户的相关信息,id是用户自己的Id，userId是他要查询的Id")
    @RequestMapping(value = "/getUserInfoByUserId",method = RequestMethod.GET)
    public ResultInfo getUserInfoByUserId(@RequestParam("id")Long id,@RequestParam("userId")Long userId){
        OtherUserInfoDto otherUserInfoDto = services.userService.getUserInfoByUserId(userId);
        List<YxqUser> users = services.userConcornsService.getInfoByUserId(id);
        int instantNewsNum = services.instantNewsService.getCountById(userId);
        int longNewsNum = services.longNewsService.getCountById(userId);
        int concernCount = services.userConcornsService.getCountByToId(userId);
        otherUserInfoDto.setStatus(0);   //表示未关注
        for(YxqUser u : users){
            if(u.getId() == new Long(userId).intValue()){
                otherUserInfoDto.setStatus(1);    //表示已关注
                break;
            }
        }

        int postNum = instantNewsNum + longNewsNum;
        List<Object> list = new ArrayList<>();
        list.add(otherUserInfoDto);
        list.add(postNum);
        list.add(concernCount);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="通过用户Id获得用户的关注信息")
    @RequestMapping(value = "/getUserConcornsByUserId/{userId}",method = RequestMethod.GET)
    public ResultInfo getUserConcornsByUserId(@PathVariable Long userId){
        List<YxqUser> list = services.userConcornsService.getInfoByUserId(userId);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }

    @ApiOperation(value="取消关注")
    @RequestMapping(value = "/deleteConcerns",method = RequestMethod.GET)
    public ResultInfo deleteConcerns(@RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId){
        if( services.userConcornsService.deleteConcernByFromIdAndToId(fromId,toId) == 0 ){
            ResultInfo result = ResultInfo.getInstance("成功",200);
            return result;
        }else{
            ResultInfo result = ResultInfo.getInstance("失败",400);
            return result;
        }
    }

    @ApiOperation(value="用户关注")
    @RequestMapping(value = "/addConcerns",method = RequestMethod.GET)
    public ResultInfo addConcerns(@RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId){
        if( services.userConcornsService.insertConcern(fromId,toId) == 0 ){
            ResultInfo result = ResultInfo.getInstance("成功",200);
            return result;
        }else{
            ResultInfo result = ResultInfo.getInstance("失败",400);
            return result;
        }
    }

    @ApiOperation(value="游戏关注")
    @RequestMapping(value = "/addGameFocus",method = RequestMethod.GET)
    public ResultInfo addGameFocus(@RequestParam("userId")Long userId,@RequestParam("gameId")Long gameId){
        YxqUserFocus userFocus = new YxqUserFocus();
        userFocus.setGameId(gameId);
        userFocus.setUserId(userId);
        if( services.userFocusService.addUserFocus(userFocus) == 0 ){
            YxqGame game = services.gameService.getGameById(gameId);
            ResultInfo result = ResultInfo.getInstance("成功",200);
            result.addContent("gameInfo",game);
            return result;
        }else{
            ResultInfo result = ResultInfo.getInstance("失败",400);
            return result;
        }
    }

    @ApiOperation(value="游戏删除关注")
    @RequestMapping(value = "/deleteGameFocus",method = RequestMethod.GET)
    public ResultInfo deleteGameFocus(@RequestParam("userId")Long userId,@RequestParam("gameId")Long gameId){
        if( services.userFocusService.delelteUserFocus(userId,gameId) == 0 ){
            ResultInfo result = ResultInfo.getInstance("成功",200);
            return result;
        }else{
            ResultInfo result = ResultInfo.getInstance("失败",400);
            return result;
        }
    }
    @ApiOperation(value="统计用户关注数和发帖数")
    @RequestMapping(value = "/countConAndFoc",method = RequestMethod.GET)
    public ResultInfo countConAndFoc(@RequestHeader("token")String token){
        long userId = services.userService.getUserId(token);
        int instantNewsNum = services.instantNewsService.getCountById(userId);
        int longNewsNum = services.longNewsService.getCountById(userId);
        long conCount = services.userConcornsService.getConcernsCount(userId);
        return ResultInfo.success("success","conCount",conCount).addContent("focCount",instantNewsNum+longNewsNum);
    }

    @ApiOperation(value="通过用户Id获得谁关注了我")
    @RequestMapping(value = "/getBeConcornsUser",method = RequestMethod.GET)
    public ResultInfo getBeConcornsUser(@RequestHeader("token")String token){
        long userId = services.userService.getUserId(token);
        List<UserInfoDto> list = services.userConcornsService.getBeConcernUserBy(userId);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }


}
