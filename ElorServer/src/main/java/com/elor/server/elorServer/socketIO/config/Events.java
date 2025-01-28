package com.elor.server.elorServer.socketIO.config;

public enum Events {

	ON_LOGIN ("onLogin"),
	ON_GET_ALL ("onGetAll"),
    ON_LOGOUT ("onLogout"),
    ON_LOGIN_ANSWER ("onLoginAnswer"),
	ON_GET_ALL_ANSWER ("onGetAllAnswer"),
	ON_RESET_PASSWORD("onResetPassword"),
	ON_RESET_PASSWORD_RESPONSE("resetPasswordResponse");
	
	public final String value;
	
	private Events(String value) {
		this.value = value;
	}
	
}