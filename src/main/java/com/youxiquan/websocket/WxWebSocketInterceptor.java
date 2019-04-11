package com.youxiquan.websocket;

import com.youxiquan.pojo.YxqUser;
import com.youxiquan.service.Services;
import com.youxiquan.util.jedis.JConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author lzb
 * 2018/12/22
 */
public class WxWebSocketInterceptor implements HandshakeInterceptor {

    private Services services;
    WxWebSocketInterceptor(){
        super();
    }
    WxWebSocketInterceptor(Services services){
        this.services = services;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest,
                                   ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler,
                                   Map<String, Object> map) throws Exception {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
        String token = servletRequest.getServletRequest().getHeader("token");
        if(StringUtils.isEmpty(token)) return false;
        String userId = services.jedisClient.hget(token, JConstants.USERID);
        YxqUser user = services.userService.getUserById(new Long(userId));
        if(user==null) return false;
        map.put("userId",userId);
        System.out.println("用户id："+user.getNickname()+"连接");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
