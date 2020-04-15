package com.a528854302.config;

import com.a528854302.interceptor.MessageHandshakeInterceptor;
import com.a528854302.handler.MyMessagHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    MyMessagHandler myMessagHandler;

    @Autowired
    MessageHandshakeInterceptor handshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(myMessagHandler,"/ws/{uid}")
                .setAllowedOrigins("*")
                .addInterceptors(handshakeInterceptor);
    }
}
