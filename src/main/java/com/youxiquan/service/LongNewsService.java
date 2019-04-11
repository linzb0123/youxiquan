package com.youxiquan.service;


import com.youxiquan.dto.LongNewsAndReplyDto;
import com.youxiquan.dto.LongNewsDto;
import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqLongNews;

import java.util.List;

/**
 * @author yzx
 * @date 2018/12/1
 */
public interface LongNewsService {

    /**
     * 根据ID获取该id的即时帖子信息
     * @param id
     * @return
     */
    YxqLongNews getLongNewsById(long id);

    /**
     * 分页获得长期帖子列表
     */
    DataTablesResult getLongNewsList(int draw, int start, int length, String search,
                                        String minDate, String maxDate, String orderCol, String orderDir);

    /**
     * 获得长期帖子总数
     * @return
     */
    DataTablesResult getLongNewsCount();

    /**
     * 更新长期帖子信息
     */
    YxqLongNews updateLongNews(Long id, YxqLongNews yxqLongNews);


    /**
     * 修改长期帖子的状态
     */
    YxqLongNews alertLongNewsStatus(Long id, Integer status);


    /**
     * 删除长期帖子
     */
    int removeLongNews(Long id);

    YxqLongNews updatePostStatus(Long id, Integer status);

    /**
     * 修改长期帖子的排序
     */
    YxqLongNews alertLongNewsOrder(Long id, Integer order);

    /**
     * 获得长期帖子的详情
     */
    LongNewsAndReplyDto getLongNewsDetail(Long id);

    /**
     * 小程序通过游戏ID来搜索与该游戏有关的帖子
     */

    List<LongNewsDto> getListByGameId(Long gameId);

    /**
     * 查询某个用户所关注的所有游戏的帖子
     */
    List<LongNewsDto> getList(Long userId);

    /**
     * 查询用户自己发的帖子
     */
    List<LongNewsDto> getMyselfByUserId(Long userId);

    /**
     * 插入长期帖子
     */
    int insertLongNews(YxqLongNews longNews);

    /**
     * 获得用户的帖子数
     */
    int getCountById(Long userId);
}
