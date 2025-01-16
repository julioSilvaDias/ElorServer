package com.elor.server.elorServer.socketIO.model;

public abstract class AbstractMessage {

	private String message = null;
	
	public AbstractMessage(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
