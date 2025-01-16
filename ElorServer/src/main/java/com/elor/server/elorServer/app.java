package com.elor.server.elorServer;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.corundumstudio.socketio.SocketIOServer;
import com.elor.server.elorServer.socketIO.SocketIOModule;

public class app {

	private static final String HOST_NAME = "0.0.0.0";
	private static final int PORT = 5000;
	
	public static void main(String[] args) {
		Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.OFF);
        
        //Configuracion del servidor
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(HOST_NAME);
        config.setPort(PORT);
        
        //Iniciar el Servidor
        SocketIOServer server = new SocketIOServer(config);
        SocketIOModule module = new SocketIOModule(server);
        module.start();
	}

}
