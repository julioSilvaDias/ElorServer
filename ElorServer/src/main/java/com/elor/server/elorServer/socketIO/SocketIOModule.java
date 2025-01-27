package com.elor.server.elorServer.socketIO;

import java.util.ArrayList;
import java.util.List;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.elor.server.elorServer.socketIO.config.Events;
import com.elor.server.elorServer.socketIO.model.MessageInput;
import com.elor.server.elorServer.socketIO.model.MessageOutput;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bbdd.GestorUsuario;
import bbdd.pojos.Usuario;

public class SocketIOModule {

	GestorUsuario gestorUsuario = new GestorUsuario();
	private SocketIOServer server = null;

	public SocketIOModule(SocketIOServer server) {

		super();
		this.server = server;

		server.addConnectListener(OnConnect());
		server.addDisconnectListener(OnDisconnect());

		server.addEventListener(Events.ON_LOGIN.value, MessageInput.class, this.login());
		server.addEventListener(Events.ON_GET_ALL.value, MessageInput.class, this.getAll());
		server.addEventListener(Events.ON_LOGOUT.value, MessageInput.class, this.logout());
	}

	private ConnectListener OnConnect() {
		return (client -> {
			client.joinRoom("default-room");
			System.out.println("New connection, Client: " + client.getRemoteAddress());
		});
	}

	private DisconnectListener OnDisconnect() {
		return (client -> {
			client.leaveRoom("default-room");
			System.out.println(client.getRemoteAddress() + " disconected from server");
		});
	}

	private DataListener<MessageInput> getAll() {
		return ((client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress() + " wants to getAll");

			List<Usuario> usuarios = new ArrayList<Usuario>();
			// añadir nuevo usuario
			usuarios.add(new Usuario());

			String answerMessage = new Gson().toJson(usuarios);

			MessageOutput messageOutput = new MessageOutput(answerMessage);
			client.sendEvent(Events.ON_GET_ALL_ANSWER.value, messageOutput);
		});
	}

	private DataListener<MessageInput> logout() {
		return ((client, data, ackSender) -> {
			System.out.println("Client form " + client.getRemoteAddress() + " wants to logout");

			String message = data.getMessage();

			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
			String username = jsonObject.get("login").getAsString();

			System.out.println(username + " loged out");
		});
	}

	private DataListener<MessageInput> login() {
		return (client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress() + " wants to login");
			System.out.println("Datos recibidos del cliente: " + data.getMessage());

			Gson gson = new Gson();

			try {
				// Parseamos el mensaje recibido
				JsonObject jsonObject = gson.fromJson(data.getMessage(), JsonObject.class);
				String username = jsonObject.get("username").getAsString();
				String password = jsonObject.get("pass").getAsString();

				// Simulamos un gestor de usuarios
				Usuario usuario = gestorUsuario.login(username, password);

				// Construimos la respuesta
				String respuesta = (usuario != null) ? "Login correcto" : "Usuario no encontrado";

				JsonObject response = new JsonObject();
				response.addProperty("message", respuesta);

				System.out.println("Respuesta enviada al cliente: " + respuesta);
				MessageOutput messageOutput = new MessageOutput(gson.toJson(response));
				client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);

			} catch (Exception e) {
				e.printStackTrace();
				String errorMensaje = "Error en el proceso de login: " + e.getMessage();
				System.out.println("Error procesando login: " + errorMensaje);

				JsonObject response = new JsonObject();
				response.addProperty("message", errorMensaje);

				MessageOutput messageOutput = new MessageOutput(gson.toJson(response));
				client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);
			}
		};
	}

	/*
	 * private DataListener<MessageInput> login() { return ((client, data,
	 * ackSender) -> { System.out.println("Client from " + client.getRemoteAddress()
	 * + " wants to login"); System.out.println("Datos recibidos del cliente: " +
	 * data.getMessage());
	 * 
	 * Gson gson = new Gson();
	 * 
	 * try { JsonObject jsonObject = gson.fromJson(data.getMessage(),
	 * JsonObject.class); String username =
	 * jsonObject.get("username").getAsString(); String password =
	 * jsonObject.get("pass").getAsString();
	 * 
	 * Usuario usuario = gestorUsuario.login(username, password);
	 * 
	 * String respuesta; if (usuario == null) { respuesta = "Usuario no encontrado";
	 * } else { respuesta = "Login correcto"; }
	 * 
	 * JsonObject response = new JsonObject(); response.addProperty("message",
	 * respuesta);
	 * 
	 * System.out.println("Respuesta enviada al cliente: " + respuesta); String
	 * mensaje = gson.toJson(response); MessageOutput messageOutput = new
	 * MessageOutput(mensaje); client.sendEvent(Events.ON_LOGIN_ANSWER.value,
	 * messageOutput);
	 * 
	 * 
	 * String respuesta; if (usuario == null) { respuesta = "Usuario no encontrado";
	 * } else { respuesta = "Login correcto"; }
	 * 
	 * String mensaje = gson.toJson(respuesta); MessageOutput messageOutput = new
	 * MessageOutput(mensaje); client.sendEvent(Events.ON_LOGIN_ANSWER.value,
	 * messageOutput);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); String errorMensaje =
	 * "Error en el proceso de login: " + e.getMessage(); MessageOutput
	 * messageOutput = new MessageOutput(gson.toJson(errorMensaje));
	 * client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput); } }); }
	 */

	public void start() {
		server.start();
		System.out.println("Server started...");
	}

	public void stop() {
		server.stop();
		System.out.println("Server stopped");
	}

}
