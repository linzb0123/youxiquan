package com.youxiquan.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.youxiquan.config.WechatConfig;
import com.youxiquan.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author lzb
 * 2018/12/6
 */
@Service
public class WxServiceImpl implements WxService {

    private static final Logger log = LoggerFactory.getLogger(WxServiceImpl.class);

    @Autowired
    private WechatConfig wechatConfig;

    private WxMaService wxService;

    @PostConstruct
    public void init(){
        wxService = new WxMaServiceImpl();
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(wechatConfig.getAppid());
        config.setSecret(wechatConfig.getAppsecret());
//        config.setToken(wechatConfig.getToken());
//        config.setAesKey(wechatConfig.getAesKey());
        wxService.setWxMaConfig(config);
    }

    @Override
    public WxMaService getWxService(){
        return this.wxService;
    }
}
