package com.a528854302.handler;

import com.a528854302.entity.Message;
import com.a528854302.entity.USER_DATA;
import com.a528854302.service.MessageService;
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

    @Autowired
    ObjectMapper objectMapper;

    private static Map<Long,WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long uid = Long.parseLong((String) session.getAttributes().get("uid"));
        sessionMap.put(uid,session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        Long uid = Long.parseLong((String) session.getAttributes().get("uid"));
        JsonNode jsonNode = objectMapper.readTree(textMessage.getPayload());
        Long toId = jsonNode.get("toId").asLong();
        String msg = jsonNode.get("msg").asText();
        Message message = new Message();
        message.setFrom(USER_DATA.getById(uid));
        message.setTo(USER_DATA.getById(toId));
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
