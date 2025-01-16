package com.elor.server.elorServer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class app {

	public static void main(String[] args) {
		Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.OFF);
	}

}
