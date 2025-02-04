package com.test.system.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.querydsl.core.Tuple;
import com.test.system.model.ChatMessage;
import com.test.system.repository.ChatDSLRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;
    //private final ChatDSLRepository chatDSLRepository;
    

    @MessageMapping("/message") // 메세지 전송
    public void sendMessage(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        template.convertAndSend("/sub", message); // 구독한 채팅방으로 메세지 전송
    }

    @GetMapping("/chatting")
    public String chat(Model model) {
    	
    	
        return "page/chat/chat"; 
    }

   
}
