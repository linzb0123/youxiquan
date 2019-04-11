package com.youxiquan.service.impl;

import com.github.pagehelper.PageHelper;
import com.youxiquan.dao.YxqMessageMapper;
import com.youxiquan.pojo.YxqMessage;
import com.youxiquan.pojo.YxqMessageExample;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.MessageService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * 2018/12/24
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger log = LoggerFactory.getLogger(LongNewsServiceImpl.class);

    @Autowired
    private Mappers mappers;

    @Override
    public int saveMessage(YxqMessage message) {
        return mappers.yxqMessageMapper.insert(message);
    }

    @Override
    public List<YxqMessage> getMessageByDate(long userId, Date latest) {
        YxqMessageExample example = new YxqMessageExample();
        YxqMessageExample.Criteria criteria = example.createCriteria();
        criteria.andToIdEqualTo(userId);
        criteria.andSendTimeGreaterThan(latest);
        example.setOrderByClause("send_time desc");
        List<YxqMessage> messages = mappers.yxqMessageMapper.selectByExample(example);
        return messages;
    }

    @Override
    public int saveNoSendMessage(YxqMessage message){
        return mappers.messageNoSendMapper.insert(message);
    }

    @Override
    public List<YxqMessage> getNoSendMessage(long userId) {
        YxqMessageExample example = new YxqMessageExample();
        YxqMessageExample.Criteria criteria = example.createCriteria();
        criteria.andToIdEqualTo(userId);
        example.setOrderByClause("send_time desc");
        List<YxqMessage> messages = mappers.messageNoSendMapper.selectByExample(example);
        return messages;
    }

    @Override
    public int deleteByIdList(List<Long> ids) {
        //读出并删除
        if(!CollectionUtils.isEmpty(ids)) {
            YxqMessageExample example1 = new YxqMessageExample();
            YxqMessageExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andIdIn(ids);
            return mappers.messageNoSendMapper.deleteByExample(example1);
        }
        return 0;
    }
}
