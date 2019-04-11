package com.youxiquan.service;


import com.youxiquan.pojo.DataTablesResult;
import com.youxiquan.pojo.YxqInstantNewsReply;
import com.youxiquan.pojo.YxqLongNewsReply;

/**
 * @author yzx
 * @date 2018/12/2
 */
public interface LongNewsReplyService {

    /**
     * 根据ID获取该id的长期帖子回帖信息

     */
    YxqLongNewsReply getLongNewsReplyById(long id);

    /**
     * 分页获得帖子回帖列表
     */
    DataTablesResult getLongNewsReplyList(int draw, int start, int length, String search,
                                             String minDate, String maxDate, String orderCol, String orderDir);

    /**
     * 获得帖子回帖总数
     */
    DataTablesResult getLongNewsReplyCount();

    /**
     * 更新帖子回帖信息
     */
    YxqLongNewsReply updateLongNewsReply(Long id, YxqLongNewsReply yxqLongNewsReply);


    /**
     * 修改帖子回帖状态
     */
    YxqLongNewsReply alertLongNewsReplyStatus(Long id, Integer status);


    /**
     * 删除帖子回帖信息
     */
    int removeLongNewsReply(Long id);

    /**
     * 添加帖子回复
     */
    int insertLongNewsReply(YxqLongNewsReply yxqLongNewsReply);
}
