package com.test.system.model;

import java.util.List;

import lombok.Data;

@Data
public class ChatMessage {

	public enum MessageType {
	    SEND,
	    EXIT
	}
	private MessageType type;
    private String contents;
    private String sender; 
    private String timestamp; 
    private String code; 
    // 세션 수와 사용자 목록을 추가
    private int sessionCount;  // 현재 세션 수
    private List<String> userList; // 사용자 목록
}
