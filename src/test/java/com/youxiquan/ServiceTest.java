package com.youxiquan;

import com.alibaba.fastjson.JSON;
import com.youxiquan.dao.YxqGameCategoryMapper;
import com.youxiquan.dao.YxqGameMapper;
import com.youxiquan.dao.YxqGameTypeMapper;
import com.youxiquan.dto.GameDto;
import com.youxiquan.pojo.YxqGame;
import com.youxiquan.pojo.YxqGameType;
import com.youxiquan.pojo.YxqUser;
import com.youxiquan.service.Mappers;
import com.youxiquan.service.Services;
import com.youxiquan.util.WebUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED,rollbackFor={Exception.class, RuntimeException.class})
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    @Autowired
    public Mappers mappers;
    @Autowired
    public Services services;

    @Test
    public void run() throws Exception {
//        YxqGame game = new YxqGame();
//        game.setPhoto("1111");
//        game.setInfo("ssssssss");
//        int x = gameMapper.insertSelective(game);
////        int x = gameMapper.insertSelective(game);
//        System.out.println("---------------------");
//        System.out.println(game.getId());
//        List<String> old= categoryMapper.selectTypeByGameId(1L);
//        List<String> old1 = new ArrayList<>(old);
//        List<String> old2= categoryMapper.selectTypeByGameId(18L);
//        old.removeAll(old2);
//        old2.removeAll(old1);
//        System.out.println("111");
//        List<String> type2 = new ArrayList<>(types);
//        List<YxqGameType> list = mappers.gameTypeMapper.selectGameTypeList("","type","desc");
//        System.out.println("sd");
//        List<GameDto> list = mappers.gameMapper.selectGameList("","order_num","asc");
//        System.out.println("ssss");
//       long x = mappers.gameMapper.countGameList("moba");
//        System.out.println(x);

//        services.jedisClient.hset("token","userId","10086");
//        services.jedisClient.hset("token","sessionKey","10010");
//        services.jedisClient.expire("token",100);
//        System.out.println(services.jedisClient.get("me"));
//        System.out.println(WebUtils.getIpAddrAdvanced());
//        YxqUser user = services.userService.getUserByOpenid("Cccc");

//        YxqUser user = new YxqUser();
//        user.setOpenid("123");
//        mappers.yxqUserMapper.insertSelective(user);
//        System.out.println(user.getId());
        List<YxqGame> list = mappers.gameMapper.getFocusGame(10L);
        System.out.println(JSON.toJSONString(list));
    }

}
