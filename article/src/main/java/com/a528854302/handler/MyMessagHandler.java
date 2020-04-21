package com.a528854302.handler;

import com.a528854302.entity.Message;
import com.a528854302.entity.TbUser;
import com.a528854302.mapper.TbUserMapper;
import com.a528854302.service.MessageService;
import com.a528854302.service.TbUserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyMessagHandler  extends TextWebSocketHandler {

    @Autowired
    MessageService messageService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TbUserMapper tbUserMapper;

    @Autowired
    ObjectMapper objectMapper;

    private static Map<String,WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uid = (String) session.getAttributes().get("uid");
        sessionMap.put(uid,session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String uid = (String) session.getAttributes().get("uid");
        String payload = textMessage.getPayload();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String toId = jsonNode.get("toId").asText();
        String msg = jsonNode.get("msg").asText();
        Message message = new Message();


        message.setFrom(tbUserMapper.selectById(uid));
        message.setTo(tbUserMapper.selectById(toId));
        message.setContent(msg);
        messageService.insert(message);
        WebSocketSession toSession = sessionMap.get(toId);
        if (toSession!=null && toSession.isOpen()){
            toSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
            message.setStatus(1);
            messageService.update(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getAttributes().get("uid"));
    }
}
