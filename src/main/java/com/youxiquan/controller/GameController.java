package com.youxiquan.controller;

import com.youxiquan.pojo.YxqGame;
import com.alibaba.fastjson.JSON;
import com.youxiquan.dto.GameDto;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqGameType;
import com.youxiquan.result.DataTablesResult;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.service.Services;
import com.youxiquan.util.Result;
import com.youxiquan.util.ResultUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
@RequestMapping("/game")
@Controller
public class GameController {

    final static org.slf4j.Logger log= LoggerFactory.getLogger(GameController.class);

    @Autowired
    private Services services;

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult geGameList(int draw, int start, int length,
                                         @RequestParam("search[value]") String search,
                                         @RequestParam("order[0][column]") int orderCol,
                                         @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","game_name","photo","info","type","number","order_num","status","create_time"};
        String orderColumn = cols[orderCol];
        //默认排序列
        if(orderColumn == null) {
            orderColumn = "order_num";
        }
        //获取排序方式 默认为desc(asc)

        if(orderDir == null) {
            orderDir = "desc";
        }
        DataTablesResult result=services.gameService.getGameList(draw,start,length,search,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/getGame/{gameId}",method = RequestMethod.GET)
    @ResponseBody
    public Result<YxqGame> getGameInfoById(@PathVariable long gameId){

        YxqGame yxqGame = services.gameService.getGameById(gameId);
        return new ResultUtil<YxqGame>().setData(yxqGame);
    }
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getGameCount(){
        return services.gameService.getGameCount();
    }

    @RequestMapping(value = "/stop",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo stopGame(@RequestParam("id") Long gameId){
        if(services.gameService.stopGame(gameId)){
            return ResultInfo.success("下架成功");
        }
        return ResultInfo.fail("游戏不存在");

    }

    @RequestMapping(value = "/putaway",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo putawayGame(@RequestParam("id") Long gameId){
        if(services.gameService.putwayGame(gameId)){
            return ResultInfo.success("上架成功");
        }
        return ResultInfo.fail("游戏不存在");

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateGame(GameDto game){
        services.gameService.updateGame(game);
        return ResultInfo.success("更新成功");

    }

    @RequestMapping(value = "/typeList",method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getAlltypes(){
        List<YxqGameType > list = services.gameService.getAllType();
        return ResultInfo.success("success","types",list);

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addGame(GameDto game){
        services.gameService.addGame(game);
        return ResultInfo.success("添加成功");

    }
    @RequestMapping(value = "/getTypeList",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getTypeList(int draw, int start, int length,
                                         @RequestParam("search[value]") String search,
                                         @RequestParam("order[0][column]") int orderCol,
                                         @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id","type"};
        String orderColumn = cols[orderCol];
        //默认排序列
        if(orderColumn == null) {
            orderColumn = "id";
        }
        //获取排序方式 默认为desc(asc)

        if(orderDir == null) {
            orderDir = "desc";
        }
        DataTablesResult result=services.gameService.getTypeList(draw,start,length,search,orderColumn,orderDir);
        return result;
    }

    @RequestMapping(value = "/TypeCount",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getGameTypeCount(){
        return services.gameService.getTypeCount();
    }

    @RequestMapping(value = "/addType",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addType(String type){
        YxqGameType type1 = new YxqGameType();
        type1.setType(type);
        services.gameService.addType(type1);
        return ResultInfo.success("添加成功");
    }


    @RequestMapping(value = "/editType",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo editType(YxqGameType type){
        services.gameService.editType(type);
        return ResultInfo.success("编辑成功");
    }

    @RequestMapping(value = "/delType",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delType(String type){
        services.gameService.delType(type);
        return ResultInfo.success("删除成功");
    }

}
