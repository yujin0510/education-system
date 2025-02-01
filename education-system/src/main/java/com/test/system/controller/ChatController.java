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

    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/message") // 메세지 전송
    public void sendMessage(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        template.convertAndSend("/sub", message); // 구독한 채팅방으로 메세지 전송
    }

    @GetMapping("/chatting")
    public String chat() {
        return "page/chat/chat"; 
    }

   
}
