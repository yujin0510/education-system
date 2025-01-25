package com.test.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.system.model.ChatMessage;

@Controller
public class ChatController {
    private final SimpMessagingTemplate template;
    
    private List<String> users = new ArrayList<>();  // 사용자 목록

    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/test") // 메세지 전송
    public void sendMessage(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        template.convertAndSend("/sub", message); // 구독한 채팅방으로 메세지 전송
        
        users.add(message.getSender());
        message.setSessionCount(users.size());
        message.setUserList(users); // 사용자 목록과 세션 수 설정
        
        template.convertAndSend("/sub", users);
    }

    @GetMapping("/chatting")
    public String chat() {
        return "page/chat"; 
    }

    // 채팅방에 접속 시 호출
    @MessageMapping("/test/enter")
    @SendTo("/chatting")
    public ChatMessage handleUserJoin(ChatMessage message) {
        users.add(message.getSender());
        message.setSessionCount(users.size());
        message.setUserList(users); // 사용자 목록과 세션 수 설정
        System.out.println(">>>" + users);
        return message;
    }

    // 채팅방에서 퇴장 시 호출
    @MessageMapping("/test/leave")
    @SendTo("/chatting")
    public ChatMessage handleUserLeave(ChatMessage message) {
        // Remove the user from the list and update session count
        users.remove(message.getSender());  // 사용자 목록에서 제거
        message.setSessionCount(users.size());  // Update session count
        message.setUserList(users);  // Update user list
        System.out.println(">>>>>>>>>" + users);
        return message;
    }
}
