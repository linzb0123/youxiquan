package com.youxiquan.service.impl;

import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqGameExample;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.WxGameService;
import com.youxiquan.util.jedis.JedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * 2018/12/20
 */
@Service
public class WxGameServiceImpl implements WxGameService {
    private static final Logger log = LoggerFactory.getLogger(WxGameServiceImpl.class);

    @Autowired
    private Mappers mappers;
    @Autowired
    public JedisClient jedisClient;

    @Override
    public List<YxqGame> getFocusGame(long userId){
        return mappers.gameMapper.getFocusGame(userId);
    }

}
