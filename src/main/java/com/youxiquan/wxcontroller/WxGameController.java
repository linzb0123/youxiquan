package com.youxiquan.wxcontroller;

import com.youxiquan.annotation.NoRequiredLogin;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.WxUserUtils;
import com.youxiquan.util.jedis.JConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzb
 * 2018/12/20
 */
@Api(description= "游戏管理")
@RequestMapping("/api/wxGame")
@RestController
public class WxGameController {

    final static org.slf4j.Logger log= LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    private Services services;

    @ApiOperation(value="获取关注的游戏圈", notes="获取关注的游戏圈")
    @RequestMapping(value = "/getFocus",method = RequestMethod.GET)
    public ResultInfo getFocusGames(){
        String userId = services.jedisClient.hget(WxUserUtils.getToken(), JConstants.USERID);
        List<YxqGame> list = services.wxGameService.getFocusGame(Long.parseLong(userId));
        return ResultInfo.success("success","focus",list);
    }

    @ApiOperation(value="获得游戏列表")
    @RequestMapping(value = "/getList/{userId}",method = RequestMethod.GET)
    public ResultInfo getList(@PathVariable Long userId){
        List<YxqGame> list = services.gameService.getListByUserId(userId);
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }


    @ApiOperation(value="通过游戏ID来获得游戏信息")
    @RequestMapping(value = "/getInfo/{gameId}",method = RequestMethod.GET)
    public ResultInfo getInfo(@PathVariable Long gameId){
        YxqGame game = services.gameService.getGameById(gameId);
        YxqGame list = new YxqGame();
        list.setId(game.getId());
        list.setGameName(game.getGameName());
        ResultInfo result = ResultInfo.getInstance("成功",200,list);
        return result;
    }
}
