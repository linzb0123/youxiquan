package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youxiquan.dto.GameDto;
import com.youxiquan.exception.RestfulException;
import com.youxiquan.pojo.*;
import com.youxiquan.result.DataTablesResult;
import com.youxiquan.service.GameService;
import com.youxiquan.service.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
@Service
public class GameServiceImpl implements GameService {

    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Override
    public DataTablesResult getGameList(int draw, int start, int length, String search, String orderCol, String orderDir) {

        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
//            PageHelper.startPage(start/length+1,length);
            List<GameDto> lists = mappers.gameMapper.selectGameList("%"+search+"%",orderCol,orderDir,start,length);
//            PageInfo<GameDto> pageInfo=new PageInfo<>(lists);

            result.setRecordsFiltered((int)getFilteredTotal("%"+search+"%"));
            result.setRecordsTotal(getGameCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(lists);

        }
        catch (Exception e)
        {
            throw new RestfulException("失败");
        }

        return result;
    }

    @Override
    public YxqGame getGameById(long id) {

        YxqGame yxqGame;
        try {
            yxqGame = mappers.gameMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RestfulException("ID获得即游戏失败");
        }
        return yxqGame;
    }
    @Override
    public DataTablesResult getGameCount(){
        DataTablesResult result = new DataTablesResult();
        YxqGameExample example = new YxqGameExample();
        try {

            result.setRecordsTotal((int) mappers.gameMapper.countByExample(example));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RestfulException("统计游戏数失败");
        }
        return result;
    }


    @Override
    public boolean stopGame(Long id){
        YxqGame game = mappers.gameMapper.selectByPrimaryKey(id);
        if(game!=null) {
            //下架后排序999999
            game.setOrderNum(999999);
            game.setStatus(1);
            mappers.gameMapper.updateByPrimaryKey(game);
            return true;
        }
       return false;
    }


    @Override
    public boolean putwayGame(Long id){
        YxqGame game = mappers.gameMapper.selectByPrimaryKey(id);
        if(game!=null) {
            //重新上架为9999
            game.setOrderNum(9999);
            game.setStatus(0);
            mappers.gameMapper.updateByPrimaryKey(game);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateGame(GameDto dto){
        try {
            //dto to pojo
            YxqGame game = new YxqGame();
            game.setInfo(dto.getInfo());
            game.setPhoto(dto.getPhoto());
            game.setNumber(dto.getNumber());
            game.setGameName(dto.getGameName());
            game.setId(dto.getId());
            game.setOrderNum(dto.getOrderNum());
            //同步更新yxq_game_category表
            YxqGame g = mappers.gameMapper.selectByPrimaryKey(game.getId());
            YxqGameCategoryExample example = new YxqGameCategoryExample();
            YxqGameCategoryExample.Criteria criteria = example.createCriteria();
            YxqGameCategory category = new YxqGameCategory();
            criteria.andGameIdEqualTo(game.getId());
            boolean flag=false;
            if(!g.getGameName().equals(game.getGameName())){
                category.setGameName(game.getGameName());
                flag=true;
            }
            if(!g.getPhoto().equals(game.getPhoto())){
                category.setGamePhoto(game.getPhoto());
                flag=true;
            }
            mappers.gameMapper.updateByPrimaryKeySelective(game);
            if(flag){
                //同步更新yxq_game_category表
                mappers.gameCategoryMapper.updateByExampleSelective(category,example);
            }
            //集合差运运算
            List<String> needDelete= mappers.gameCategoryMapper.selectTypeByGameId(game.getId());
            List<String> types = dto.getTypes();
            List<String> needInsert = new ArrayList<>(types);
            needInsert.removeAll(needDelete);
            needDelete.removeAll(types);
            //新增
            if(!needInsert.isEmpty()){
                for(String s:types){
                    YxqGameCategory cc = new YxqGameCategory();
                    cc.setType(s);
                    cc.setGameName(game.getGameName());
                    cc.setGamePhoto(game.getPhoto());
                    cc.setGameId(game.getId());
                    mappers.gameCategoryMapper.insert(cc);
                }
            }
            //删除
            if(!needDelete.isEmpty()){
                    YxqGameCategory cc = new YxqGameCategory();
                    YxqGameCategoryExample example1 = new YxqGameCategoryExample();
                    YxqGameCategoryExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andGameIdEqualTo(game.getId());
                    criteria1.andTypeIn(needDelete);
                    mappers.gameCategoryMapper.deleteByExample(example1);
            }

        }catch (Exception e){
            throw new RestfulException("更新失败");
        }
        return true;
    }

    @Override
    public List<YxqGameType> getAllType(){
        YxqGameTypeExample example =new YxqGameTypeExample();
        List<YxqGameType> types = mappers.gameTypeMapper.selectByExample(example);
        return types;

    }

    @Override
    @Transactional
    public boolean addGame(GameDto gameDto){
        try {
            YxqGame game = new YxqGame();
            game.setCreateTime(new Date());
            game.setStatus(2);
            game.setOrderNum(gameDto.getOrderNum());
            game.setGameName(gameDto.getGameName());
            game.setInfo(gameDto.getInfo());
            game.setNumber(gameDto.getNumber());
            game.setPhoto(gameDto.getPhoto());
            mappers.gameMapper.insertSelective(game);
            Long gameId = game.getId();
            List<String> types = gameDto.getTypes();
            for(String s:types){
                YxqGameCategory category = new YxqGameCategory();
                category.setType(s);
                category.setGameName(game.getGameName());
                category.setGamePhoto(game.getPhoto());
                category.setGameId(gameId);
                mappers.gameCategoryMapper.insert(category);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RestfulException("添加失败");
        }
        return true;
    }


    @Override
    public DataTablesResult getTypeList(int draw, int start, int length, String search, String orderCol, String orderDir) {

        DataTablesResult result=new DataTablesResult();
        try
        {
            //分页
            PageHelper.startPage(start/length+1,length);
            List<YxqGameType> lists = mappers.gameTypeMapper.selectGameTypeList("%"+search+"%",orderCol,orderDir);
            PageInfo<YxqGameType> pageInfo=new PageInfo<>(lists);
//
            result.setRecordsFiltered((int)pageInfo.getTotal());
            result.setRecordsTotal(getTypeCount().getRecordsTotal());
//
            result.setDraw(draw);
            result.setData(lists);

        }
        catch (Exception e)
        {
            throw new RestfulException("失败");
        }

        return result;
    }

    @Override
    public DataTablesResult getTypeCount(){
        DataTablesResult result = new DataTablesResult();
        YxqGameTypeExample example = new YxqGameTypeExample();
        try {
            result.setRecordsTotal((int) mappers.gameTypeMapper.countByExample(example));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RestfulException("统计失败");
        }
        return result;
    }
    @Override
    @Transactional
    public boolean editType(YxqGameType type){
        try{
            YxqGameType type1 = mappers.gameTypeMapper.selectByPrimaryKey(type.getId());
            mappers.gameTypeMapper.updateByPrimaryKeySelective(type);
            YxqGameCategoryExample example = new YxqGameCategoryExample();
            YxqGameCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andTypeEqualTo(type1.getType());
            YxqGameCategory category = new YxqGameCategory();
            category.setType(type.getType());
            mappers.gameCategoryMapper.updateByExampleSelective(category,example);
        }catch (Exception e){
            throw new RestfulException("编辑类型失败");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delType(String typeName){
        try{
            YxqGameCategoryExample example = new YxqGameCategoryExample();
            YxqGameCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andTypeEqualTo(typeName);
            YxqGameTypeExample example1 = new YxqGameTypeExample();
            YxqGameTypeExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andTypeEqualTo(typeName);
            mappers.gameTypeMapper.deleteByExample(example1);
            mappers.gameCategoryMapper.deleteByExample(example);
        }catch (Exception e){
            log.error("删除类型失败");
            log.error(e.getMessage());
            throw new RestfulException("删除失败");
        }
        return true;
    }

    @Override
    public boolean addType(YxqGameType type){
        try{
            mappers.gameTypeMapper.insertSelective(type);
        }catch (Exception e){
            throw new RestfulException("添加失败");
        }
        return true;
    }


    public long getFilteredTotal(String search){
        return mappers.gameMapper.countGameList(search);
    }


    @Override
    public List<YxqGame> getListByUserId(Long userId) {
        List<YxqGame> allGame = null;
        List<YxqGame> userGame = null;
//        List<List<YxqGame>> list = new ArrayList<List<YxqGame>>();
        try{
            allGame = mappers.gameMapper.getList();
            userGame = mappers.gameMapper.getFocusGame(userId);
            for(YxqGame all : allGame){
                for(YxqGame user : userGame){
                    if(all.getId() == user.getId()){
                        all.setStatus(1);
                        break;
                    }
                }
            }
        }catch (Exception e){
            throw new RestfulException("添加失败");
        }
        return allGame;
    }
}
