package com.youxiquan.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lzb
 * 2018/12/6
 */
public class WxMaConfiguration {

    @Autowired
    private WechatConfig wxConfig;

    private static WxMaService wxService = new WxMaServiceImpl();

//    public static WxMaService getMaService() {
//        wxService = new WxMaServiceImpl();
//        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
//        config.setAppid(wxConfig.getAppid());
//        config.setSecret(wxConfig.getSecret());
//        config.setToken(wxConfig.getToken());
//        config.setAesKey(wxConfig.getAesKey());
//        config.setMsgDataFormat(wxConfig.getMsgDataFormat());
//
//        return wxService;
//    }
}
