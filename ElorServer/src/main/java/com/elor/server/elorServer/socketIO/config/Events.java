package com.elor.server.elorServer.socketIO.config;

public enum Events {

	ON_LOGIN ("onLogin"),
    ON_LOGIN_ANSWER ("onLoginAnswer"),
	ON_GET_USER_ID("onGetUserId"),
	ON_GET_USER_ID_ANSWER("onGetUserIdAnswer");
	
	public final String value;
	
	
	private Events(String value) {
		this.value = value;
	}
	
}
