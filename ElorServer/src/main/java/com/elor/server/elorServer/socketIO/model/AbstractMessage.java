package com.elor.server.elorServer.socketIO.model;

public abstract class AbstractMessage {

	private String message = null;
	private String contentType = "application/json; charset=utf-8";

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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}