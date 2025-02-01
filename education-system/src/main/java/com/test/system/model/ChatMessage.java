package com.test.system.model;

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
    private String num; 
}

