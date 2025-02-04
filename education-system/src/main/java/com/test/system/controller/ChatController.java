package com.test.system.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.system.entity.ViewClassName;
import com.test.system.model.ChatMessage;
import com.test.system.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService;
    

    @MessageMapping("/message") // 메세지 전송
    public void sendMessage(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        template.convertAndSend("/sub", message); // 구독한 채팅방으로 메세지 전송
    }

    @GetMapping("/chatting")
    public String chat(Model model) {
    	
    	List<ViewClassName> list= chatService.findAll();
    	System.out.println(list);
    	model.addAttribute("list",list);
    	return "page/chat/chat"; 
    }

   
}
