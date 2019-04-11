package com.youxiquan.service;


import com.youxiquan.dto.InstantNewsAndReplyDto;
import com.youxiquan.dto.InstantNewsDto;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqInstantNews;

import java.util.List;

/**
 * @author yzx
 * @date 2018/11/30
 */
public interface InstantNewsService {

    /**
     * 根据ID获取该id的即时帖子信息
     * @param id
     * @return
     */
    YxqInstantNews getInstantNewsById(long id);

    /**
     * 分页获得即时帖子列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getInstantNewsList(int draw, int start, int length, String search,
                                 String minDate, String maxDate, String orderCol, String orderDir);

    /**
     * 获得即时帖子总数
     * @return
     */
    DataTablesResult getInstantNewsCount();

    /**
     * 更新帖子信息
     */
    YxqInstantNews updateInstantNews(Long id,YxqInstantNews yxqInstantNews);


    /**
     * 修改帖子的状态
     */
    YxqInstantNews alertInstantNewsStatus(Long id, Integer status);


    /**
     * 删除帖子
     */
    int removeInstantNews(Long id);

    /**
     * 获得帖子的详情
     */
    InstantNewsAndReplyDto getInstantNewsDetail(Long id);


    /**
     * 小程序通过游戏ID来搜索与该游戏有关的帖子
     */

    List<InstantNewsDto> getListByGameId(Long gameId);

    /**
     * 查询某个用户所关注的所有游戏的帖子
     */
    List<InstantNewsDto> getList(Long userId);


    /**
     * 获得自己即时帖子的发帖记录
     */
    List<InstantNewsDto> getMyselfByUserId(Long userId);

    /**
     * 用户添加帖子
     */
    int insertInstantsNews(YxqInstantNews yxqInstantNews);

    /**
     * 获得用户的发帖数目
     */
    int getCountById(long userId);
}
