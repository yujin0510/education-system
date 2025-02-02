package com.test.system.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketService extends TextWebSocketHandler {
	// WebSocket Session들을 관리하는 리스트입니다.
		private static final ConcurrentHashMap<String, WebSocketSession> clientSession = new ConcurrentHashMap<>();

		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("[+] afterConnectionEstablished :: " + session.getId());
			clientSession.put(session.getId(), session);
		}

		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("[+] handleTextMessage :: " + session);
			System.out.println("[+] handleTextMessage :: " + message.getPayload());

			clientSession.forEach((key, value) -> {
				System.out.println("key :: " + key + "  value :: " + value);
				if (!key.equals(session.getId())) { // 같은 아이디가 아니면 메시지를 전달합니다.
					try {
						value.sendMessage(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
			clientSession.remove(session);
			System.out.println("[+] afterConnectionClosed - Session: " + session.getId() + ", CloseStatus: " + status);
		}

}
