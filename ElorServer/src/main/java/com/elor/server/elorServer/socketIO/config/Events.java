package com.elor.server.elorServer.socketIO.config;

public enum Events {

	ON_LOGIN ("onLogin"),
    ON_LOGIN_ANSWER ("onLoginAnswer"),
	ON_GET_USER_ID("onGetUserId"),
	ON_GET_USER_ID_ANSWER("onGetUserIdAnswer"),
	ON_GET_HORARIO("onGetHorario"),
	ON_GET_HORARIO_ANSWER("onGetHorarioAnswer"),
//	ON_REGISTER("onRegister"),
//	ON_REGISTER_ANSWER("onRegisterAnswer"),
	ON_CHANGE_PASSWORD("onChangePassword"),
	ON_CHANGE_PASSWORD_ANSWER("onChangePasswordAnswer")
	;
	
	public final String value;
	
	private Events(String value) {
		this.value = value;
	}
	
}
