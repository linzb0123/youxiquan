package com.youxiquan.service;

import com.youxiquan.pojo.YxqMessage;

import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/12/24
 */
public interface MessageService {

    public int saveMessage(YxqMessage message);

    public List<YxqMessage> getMessageByDate(long userId,Date latest);

    int saveNoSendMessage(YxqMessage message);

    List<YxqMessage> getNoSendMessage(long userId);

    int deleteByIdList(List<Long> ids);
}
