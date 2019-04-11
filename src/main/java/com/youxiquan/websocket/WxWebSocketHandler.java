package com.youxiquan.websocket;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youxiquan.pojo.YxqMessage;
import com.youxiquan.service.Services;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

/**
 * @author lzb
 * 2018/12/22
 */
@Component
public class WxWebSocketHandler implements WebSocketHandler {

    @Autowired
    private Services services;


    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生
    public static final Map<String, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<String, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String userId = (String)webSocketSession.getAttributes().get("userId");
        if(userSocketSessionMap.get(userId)==null){
            userSocketSessionMap.put(userId, webSocketSession);
        }
        System.out.println(userId+"建立连接");
        //检查是否有未发生的消息
        List<YxqMessage> messageList = services.messageService.getNoSendMessage(new Long(userId));
        if(!CollectionUtils.isEmpty(messageList)){
            if(sendMessageListToUser(userId,messageList)){
                List<Long> ids = new ArrayList<>();
                for(YxqMessage msg: messageList){
                    ids.add(msg.getId());
                }
                services.messageService.deleteByIdList(ids);
            }
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if(webSocketMessage.getPayloadLength()==0)return;
        String json =webSocketMessage.getPayload().toString();
        YxqMessage msg = JSON.parseObject(json,YxqMessage.class);
        sendMessageToUser(msg.getToId().toString(),msg);

    }
    // 后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    // 用户退出后的处理
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        String userId =(String) webSocketSession.getAttributes().get("userId");
        userSocketSessionMap.remove(userId);
        System.out.println(userId + " close connection ");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 发送消息给指定的用户
     */
    public void sendMessageToUser(String userId,YxqMessage msg) {
        WebSocketSession user = userSocketSessionMap.get(userId);
        //isOpen()在线就发送
        if(user!=null&&user.isOpen()) {
            try {
                user.sendMessage(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //保持到未发送列表
            services.messageService.saveNoSendMessage(msg);
        }
        //保存到数据库
        services.messageService.saveMessage(msg);
    }

    /**
     * 发送多条消息给指定的用户
     */
    public boolean sendMessageListToUser(String userId,List<YxqMessage> msgs) {
        WebSocketSession user = userSocketSessionMap.get(userId);
        //isOpen()在线就发送
        if(user!=null&&user.isOpen()) {
            try {
                user.sendMessage(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msgs)));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
