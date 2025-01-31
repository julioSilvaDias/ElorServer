package com.elor.server.elorServer.socketIO.config;

public enum Events {

	ON_LOGIN ("onLogin"),
    ON_LOGIN_ANSWER ("onLoginAnswer"),
	ON_GET_ALL_ANSWER ("onGetAllAnswer"),
	ON_RESET_PASSWORD("onResetPassword"),
	ON_RESET_PASSWORD_RESPONSE("resetPasswordResponse"),
	ON_GET_USER_ID("onGetUserId"),
	ON_GET_USER_ID_ANSWER("onGetUserIdAnswer"),
	ON_GET_HORARIO("onGetHorario"),
	ON_GET_HORARIO_ANSWER("onGetHorarioAnswer");
	
	public final String value;
	
	
	private Events(String value) {
		this.value = value;
	}
	
}