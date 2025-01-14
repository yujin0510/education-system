package com.test.system.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	// 현재 채팅에 참여한 모든 사람들
	private static List<Session> sessionList;
	static {
		sessionList = new ArrayList<Session>();
	}
	
	@OnOpen
	public void handleOpen(Session session) {// new WebSocket(url)을 관리함 > HTTPSession과 다름 
		System.out.println("[LOG]클라이언트가 접속했습니다.");
		sessionList.add(session);
		//System.out.println(sessionList.size());
	}
	
	@OnMessage
	public void handleMessage(String msg, Session session) throws JsonMappingException, JsonProcessingException {
		// 메시지 많이 주고 받아서 로그 확인 안함
		System.out.println(msg);
		//{"code":1,"sender":"강아지","receiver":"","content":"","regdate":"2024-10-24 12:32:41"}
		
		ObjectMapper objectMapper = new ObjectMapper();
		ChatDTO message = null;
		message = objectMapper.readValue(msg, ChatDTO.class);
		
		//ChatDTO message = gson.fromJson(msg, ChatDTO.class); // json을 java객체로 바꿔줌
		//System.out.println(message); 
		//Message(code=1, sender=강아지, receiver=, content=, regdate=2024-10-24 12:39:17)
		
		if(message.getCode().equals("1")) {
			
			// 새로운 유저가 접속했습니다.
			
			for(Session s : sessionList) {
				if(s != session) { // 나를 제외한 다른 사람들
					
					try {
						s.getBasicRemote().sendText(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if(message.getCode().equals("2")) {
			
			// 기존 유저가 나갔습니다. 
			sessionList.remove(session);
			
			for(Session s : sessionList) {
				
				try {
					s.getBasicRemote().sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else if(message.getCode().equals("3")||message.getCode().equals("4")) {
			
			//System.out.println(message);
			// 클라이언트가 보낸 메시지를 다른 클라이언트에게 전달
			for(Session s : sessionList) {
				if(s != session) {
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
		System.out.println(session);
		
		
	}
	
	@OnError
	public void handleError(Throwable e) {
		System.out.println("[LOG] 에러가 발생했습니다." + e.getMessage());
	}
	
	
	

	
}
