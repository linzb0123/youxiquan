package com.youxiquan.websocket;

import com.youxiquan.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.io.Serializable;

/**
 * @author lzb
 * 2018/12/22
 */
@Component
@EnableWebSocket
public class WxWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    WxWebSocketHandler handler;

    @Autowired
    private Services services;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //添加websocket处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(handler, "/ws/yxq").addInterceptors(new WxWebSocketInterceptor(services)).setAllowedOrigins("*");
    }
}
