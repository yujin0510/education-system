package com.test.system.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.system.model.ChatDTO;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatService")
public class ChatService {

    // 세션과 사용자명을 매핑할 Map
    private static Map<Session, String> sessionList = new HashMap<>();
    
    @OnOpen
    public void handleOpen(Session session) {
        System.out.println("[LOG]클라이언트가 접속했습니다.");
        sessionList.put(session, null); // 초기에는 사용자 이름이 설정되지 않음
    }

    @OnMessage
    public void handleMessage(String msg, Session session) throws JsonMappingException, JsonProcessingException {
        System.out.println(msg);
        ObjectMapper objectMapper = new ObjectMapper();
        ChatDTO message = objectMapper.readValue(msg, ChatDTO.class);

        if (message.getCode().equals("1")) { // 새로운 유저가 접속
            // 사용자 이름을 세션에 매핑
            sessionList.put(session, message.getSender());

            // 모든 클라이언트에게 "000님이 들어왔습니다" 메시지 전송
            for (Session s : sessionList.keySet()) {
                if (s != session) {
                    try {
                        s.getBasicRemote().sendText(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (message.getCode().equals("2")) { // 기존 유저가 나갔을 때
            sessionList.remove(session);

            for (Session s : sessionList.keySet()) {
                try {
                    s.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (message.getCode().equals("3") || message.getCode().equals("4")) { // 메시지 또는 이모티콘
            for (Session s : sessionList.keySet()) {
                if (s != session) {
                    try {
                        s.getBasicRemote().sendText(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @OnClose
    public void handleClose(Session session) {
        System.out.println("[LOG]클라이언트가 접속 종료했습니다.");
        sessionList.remove(session);
    }

    @OnError
    public void handleError(Throwable e) {
        System.out.println("[LOG] 에러가 발생했습니다." + e.getMessage());
    }
}
