package com.youxiquan.service;

import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqUserFocus;

import java.util.List;

/**
 * @author yzx
 * 2018/12/6
 */
public interface UserFocusService {


    /**
     * 根据ID获取该id的关注信息
     */
    YxqUserFocus getUserFocusById(long id);

    /**
     * 分页获得用户关注列表
     */
    DataTablesResult getUserFocusList(int draw, int start, int length, String search,
                                 String orderCol, String orderDir);

    /**
     * 获得用户关注总数
     * @return
     */
    DataTablesResult getUserFocusCount();


    /**
     * 删除用户关注
     */
    long removeUserFocus(Long id);


    /**
     * 小程序通过用户的ID查看用户关注了哪些游戏
     */
    List<YxqUserFocus> getListByUserId(long userId);

    /**
     * 用户删除游戏关注
     */
    int delelteUserFocus(long userId,long gameId);

    /**
     * 用户添加游戏关注
     */
    int addUserFocus(YxqUserFocus UserFocus);
}
