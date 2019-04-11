package com.youxiquan.service;

import com.youxiquan.util.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {
    @Autowired
    public Mappers mappers;
    @Autowired
    public JedisClient jedisClient;
    @Autowired
    public AdminService adminService;
    @Autowired
    public InstantNewsService instantNewsService;
    @Autowired
    public GameService gameService;
    @Autowired
    public InstantNewsReplyService instantNewsReplyService;
    @Autowired
    public LongNewsService longNewsService;
    @Autowired
    public LongNewsReplyService longNewsReplyService;
    @Autowired
    public UserService userService;
    @Autowired
    public UserFocusService userFocusService;
//    @Autowired
//    public MessageService messageService;
    @Autowired
    public UserConcornsService userConcornsService;
    @Autowired
    public WxGameService wxGameService;
    @Autowired
    public MessageService messageService;
}
