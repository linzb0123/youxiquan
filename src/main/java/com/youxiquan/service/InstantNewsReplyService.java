package com.youxiquan.service;


import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqInstantNews;
import com.youxiquan.pojo.YxqInstantNewsReply;

/**
 * @author yzx
 * @date 2018/12/1
 */
public interface InstantNewsReplyService {

    /**
     * 根据ID获取该id的即时帖子回帖信息

     */
    YxqInstantNewsReply getInstantNewsReplyById(long id);

    /**
     * 分页获得即时帖子回帖列表
     */
    DataTablesResult getInstantNewsReplyList(int draw, int start, int length, String search,
                                             String minDate, String maxDate, String orderCol, String orderDir);

    /**
     * 获得即时帖子回帖总数
     */
    DataTablesResult getInstantNewsReplyCount();

    /**
     * 更新帖子回帖信息
     */
    YxqInstantNewsReply updateInstantNewsReply(Long id, YxqInstantNewsReply yxqInstantNewsReply);


    /**
     * 修改帖子回帖状态
     */
    YxqInstantNewsReply alertInstantNewsReplyStatus(Long id, Integer status);


    /**
     * 删除帖子回帖信息
     */
    int removeInstantNewsReply(Long id);

    /**
     * 添加评论帖子
     */
    int insertInstantNewsReply(YxqInstantNewsReply yxqInstantNewsReply);

}
