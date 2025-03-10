package com.enssel.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connected to " + session.getRemoteAddress());
        CLIENTS.put(session.getId(), session);
        broadcastMessage("TEST");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Disconnected from " + session.getRemoteAddress());
        CLIENTS.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId();  //메시지를 보낸 아이디
        log.info("Recevied text message: " + message.getPayload());

    }
    public void broadcastMessage(Object data) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(data);
        for (WebSocketSession session : CLIENTS.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(jsonMessage));
            }
        }
    }
}
