package com.youxiquan.service;

import com.youxiquan.pojo.YxqGame;
import com.youxiquan.dto.GameDto;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqGameType;
import com.youxiquan.result.DataTablesResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lzb
 * 2018/11/30
 */
public interface GameService {

    /**
     * datatables 返回
     * @param draw
     * @param start
     * @param length
     * @param search
     * @param orderCol
     * @param orderDir
     * @return
     */
    DataTablesResult getGameList(int draw, int start, int length, String search, String orderCol, String orderDir);

    /**
     * 根据ID获取该id的游戏信息
     * @param id
     * @return
     */
    YxqGame getGameById(long id);


    /**
     * 游戏的数目
     * @return
     */
    DataTablesResult getGameCount();

    /**
     * 下架
     * @param id
     * @return
     */
    boolean stopGame(Long id);

    /**
     * 上架
     * @param id
     * @return
     */
    boolean putwayGame(Long id);

    /**
     * 更新
     * @param game
     * @return
     */
    boolean updateGame(GameDto game);

    /**
     * 获取所有的游戏类别 一般用于checckbox
     */
    List<YxqGameType> getAllType();

    /**
     * 添加游戏
     * @param game
     * @return
     */
    boolean addGame(GameDto game);

    /**
     * 获取游戏类型列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @param orderCol
     * @param orderDir
     * @return
     */
    DataTablesResult getTypeList(int draw, int start, int length, String search, String orderCol, String orderDir);

    /**
     * 统计数目
     * @return
     */
    DataTablesResult getTypeCount();

    /**
     * 编辑
     * @param type
     * @return
     */
    boolean editType(YxqGameType type);

    /**
     * 删除
     * @param typeName
     * @return
     */
    boolean delType(String typeName);

    /**
     * 添加
     * @param type
     * @return
     */
    boolean addType(YxqGameType type);

    /**
     * 查找所有的游戏以及用户已经关注的游戏
     */
    List<YxqGame> getListByUserId(Long userId);
}
