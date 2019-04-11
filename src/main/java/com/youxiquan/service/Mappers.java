package com.youxiquan.service;

import com.youxiquan.dao.*;
import com.youxiquan.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mappers {

    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public YxqInstantNewsMapper yxqInstantNewsMapper;
    @Autowired
    public YxqGameMapper gameMapper;
    @Autowired
    public YxqInstantNewsReplyMapper yxqInstantNewsReplyMapper;
    @Autowired
    public YxqLongNewsMapper yxqLongNewsMapper;
    @Autowired
    public YxqLongNewsReplyMapper yxqLongNewsReplyMapper;
    @Autowired
    public YxqGameTypeMapper gameTypeMapper;
    @Autowired
    public YxqGameCategoryMapper gameCategoryMapper;
 	@Autowired
    public YxqUserMapper yxqUserMapper;
 	@Autowired
    public YxqUserFocusMapper yxqUserFocusMapper;
 	@Autowired
    public YxqMessageMapper yxqMessageMapper;
 	@Autowired
    public YxqUserConcernsMapper yxqUserConcernsMapper;
 	@Autowired
 	public MessageNoSendMapper messageNoSendMapper;
}
