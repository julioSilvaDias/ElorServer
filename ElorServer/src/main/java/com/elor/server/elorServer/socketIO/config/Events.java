package com.elor.server.elorServer.socketIO.config;

public enum Events {

	ON_LOGIN ("onLogin"),
    ON_LOGIN_ANSWER ("onLoginAnswer"),
	ON_GET_USER_ID("onGetUserId"),
	ON_GET_USER_ID_ANSWER("onGetUserIdAnswer"),
	ON_GET_HORARIO("onGetHorario"),
	ON_GET_HORARIO_ANSWER("onGetHorarioAnswer"),
	ON_RESET_PASSWORD("onResetPassword"),
	ON_RESET_PASSWORD_RESPONSE("resetPasswordResponse"),
	ON_GET_MEETINGS("onGetMeetings"),
	ON_GET_MEETINGS_ANSWER("onGetMeetingsAnswer"),
	ON_GET_ALL_CURSOS("onGetAllCursos"),
	ON_GET_ALL_CURSOS_ANSWER("onGetAllCursosAnswer");
	
	public final String value;
		
	private Events(String value) {
		this.value = value;
	}
	
}