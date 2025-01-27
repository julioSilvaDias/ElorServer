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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import bbdd.GestorHorario;
import bbdd.GestorUsuario;
import bbdd.HibernateProxyTypeAdapter;
import bbdd.pojos.Horario;
import bbdd.pojos.Usuario;
import bbdd.pojos.DTO.UsuarioDTO;

public class SocketIOModule {

	GestorUsuario gestorUsuario = new GestorUsuario();
	GestorHorario gestorHorario = new GestorHorario();
	private SocketIOServer server = null;
	private GsonBuilder b = new GsonBuilder();

	public SocketIOModule(SocketIOServer server) {

		super();
		this.server = server;

		b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

		server.addConnectListener(OnConnect());
		server.addDisconnectListener(OnDisconnect());

		server.addEventListener(Events.ON_LOGIN.value, MessageInput.class, this.login());
		server.addEventListener(Events.ON_GET_USER_ID.value, MessageInput.class, this.getUserId());
		server.addEventListener(Events.ON_GET_HORARIO.value, MessageInput.class, this.getHorario());
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
		return ((client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress() + " wants to login");
			System.out.println("Datos recibidos del cliente: " + data.getMessage());

			Gson gson = new Gson();

			try {
				JsonObject jsonObject = gson.fromJson(data.getMessage(), JsonObject.class);
				String name = jsonObject.get("username").getAsString();
				String password = jsonObject.get("pass").getAsString();

				Usuario usuario = gestorUsuario.login(name, password);

				String respuesta;
				if (usuario == null) {
					respuesta = "Usuario no encontrado";
				} else {
					respuesta = "Login correcto";
				}

				String mensaje = gson.toJson(respuesta);
				MessageOutput messageOutput = new MessageOutput(mensaje);
				client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);

			} catch (Exception e) {
				e.printStackTrace();
				String errorMensaje = "Error en el proceso de login";
				MessageOutput messageOutput = new MessageOutput(gson.toJson(errorMensaje));
				client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);
			}
		});
	}

	private DataListener<MessageInput> getUserId() {
		return ((client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress());
			System.out.println("Datos recibidos del cliente: " + data.getMessage());

			Gson gson = b.create();

			try {
				JsonObject jsonObject = gson.fromJson(data.getMessage(), JsonObject.class);
				String name = jsonObject.get("username").getAsString();

				Usuario usuario = gestorUsuario.getUserId(name);
				UsuarioDTO usuarioDTO = (usuario != null) ? new UsuarioDTO(usuario) : null;

				String mensaje = gson.toJson(usuarioDTO);
				MessageOutput messageOutput = new MessageOutput(mensaje);
				client.sendEvent(Events.ON_GET_USER_ID_ANSWER.value, messageOutput);

			} catch (Exception e) {
				e.printStackTrace();
				String errorMensaje = "Error en el proceso...";
				MessageOutput messageOutput = new MessageOutput(gson.toJson(errorMensaje));
				client.sendEvent(Events.ON_GET_USER_ID_ANSWER.value, messageOutput);
			}
		});
	}

	private DataListener<MessageInput> getHorario() {
		return ((client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress());
			System.out.println("Datos recibidos del cliente: " + data.getMessage());

			Gson gson = b.create();

			try {
				JsonObject jsonObject = gson.fromJson(data.getMessage(), JsonObject.class);
				String userId = jsonObject.get("message").getAsString();
				int id = Integer.parseInt(userId);
				List<Horario> horarios = gestorHorario.getHorarioById(id);

				String mensaje = gson.toJson(horarios);
				MessageOutput messageOutput = new MessageOutput(mensaje);
				client.sendEvent(Events.ON_GET_HORARIO_ANSWER.value, messageOutput);

			} catch (Exception e) {
				e.printStackTrace();
				String errorMensaje = "Error en el proceso...";
				MessageOutput messageOutput = new MessageOutput(gson.toJson(errorMensaje));
				client.sendEvent(Events.ON_GET_HORARIO_ANSWER.value, messageOutput);
			}
		});
	}

	public void start() {
		server.start();
		System.out.println("Server started...");
	}

	public void stop() {
		server.stop();
		System.out.println("Server stopped");
	}

}
