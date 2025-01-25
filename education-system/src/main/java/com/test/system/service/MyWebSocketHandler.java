package com.test.system.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    // 사용자 세션을 관리할 Map
    private Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	String sessionId = session.getId();
        sessionMap.put(sessionId, session);  // 세션 추가
        System.out.println(sessionId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessionMap.remove(sessionId);  // 세션 제거
    }
}
