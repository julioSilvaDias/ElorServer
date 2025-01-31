package com.elor.server.elorServer.socketIO.model;

public class MessageInput extends AbstractMessage{

	private String email;
	
	public MessageInput (String message, String email) {
		super(message);
		this.email = email;
	}
	
	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
