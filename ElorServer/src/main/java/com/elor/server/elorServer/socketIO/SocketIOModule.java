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
import bbdd.pojos.Usuario;
import bbdd.pojos.DTO.HorarioDTO;
import bbdd.pojos.DTO.UsuarioDTO;

public class SocketIOModule {

	private EmailService emailService;
	private GestorUsuario gestorUsuario;
	GestorHorario gestorHorario = new GestorHorario();
	private SocketIOServer server = null;
	private GsonBuilder b = new GsonBuilder();

	public SocketIOModule(SocketIOServer server) {

		super();
		this.server = server;

		b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

		/**
		 * Configuracion para el servicio del correo Se usa el puerto 587
		 */
		String senderEmail = "mariajose.suarezhu@elorrieta-errekamari.com";
		String senderPassword = "";
		String smtpHost = "smtp.gmail.com";
		int smtpPort = 587;

		this.emailService = new EmailService(senderEmail, senderPassword, smtpHost, smtpPort);
	    this.gestorUsuario = new GestorUsuario(emailService);
		
		server.addConnectListener(OnConnect());
		server.addDisconnectListener(OnDisconnect());

		server.addEventListener(Events.ON_LOGIN.value, MessageInput.class, this.login());
		server.addEventListener(Events.ON_GET_ALL.value, MessageInput.class, this.getAll());
		server.addEventListener(Events.ON_LOGOUT.value, MessageInput.class, this.logout());
		server.addEventListener(Events.ON_GET_USER_ID.value, MessageInput.class, this.getUserId());
		server.addEventListener(Events.ON_GET_HORARIO.value, MessageInput.class, this.getHorario());
		server.addEventListener(Events.ON_RESET_PASSWORD.value, MessageInput.class, this.resetPassword());

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
				/**
				 * Parsear el mensaje recibido
				 */
				JsonObject jsonObject = gson.fromJson(data.getMessage(), JsonObject.class);
				String username = jsonObject.get("username").getAsString();
				String password = jsonObject.get("pass").getAsString();

				Usuario usuario = gestorUsuario.login(username, password);

				/**
				 * Validación para verificar si el usuario existe en la base de datos
				 */
				if (usuario == null) {
					JsonObject response = new JsonObject();
					response.addProperty("message", "El Usuario no es alumno del centro.");
					MessageOutput messageOutput = new MessageOutput(gson.toJson(response));
					client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);
					return;
				}

				/**
				 * Si la contraseña es predeterminada tendra que registrarse para hacer el
				 * cambio de contraseña
				 */

				if ("Elorrieta00".equalsIgnoreCase(password)) {
					JsonObject response = new JsonObject();
					response.addProperty("message", "El usuario debe de registrarse.");
					MessageOutput messageOutput = new MessageOutput(gson.toJson(response));
					client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);
					return;
				}

				/**
				 * En el caso de que la contraseña sea incorrecta
				 */

				if (!usuario.getPassword().equals(password)) {
					JsonObject response = new JsonObject();
					response.addProperty("message", "Login y/o Pass incorrecto");
					MessageOutput messageOutput = new MessageOutput(gson.toJson(response));
					client.sendEvent(Events.ON_LOGIN_ANSWER.value, messageOutput);
					return;
				}
				/**
				 * Si el Login es correcto
				 */

				JsonObject response = new JsonObject();
				response.addProperty("message", "Login correcto");
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

	private DataListener<MessageInput> resetPassword() {
		return (client, data, ackSender) -> {

			/**
			 * Obtiene el email del usuario
			 */
			String email = data.getEmail();
			System.out.println("Solicitando restablecimiento de contraseña para: " + email);

			Usuario usuario = gestorUsuario.obtenerUsuarioPorEmail(email);

			/**
			 * Si el usuario no existe en la base de datos, le salta un mensaje diciendole
			 * que no es alumno del centro.
			 */
			if (usuario == null) {
				JsonObject response = new JsonObject();
				response.addProperty("message", "Usuario no es alumno del centro.");
				client.sendEvent(Events.passwordResetError.value, response);
				return;
			}

			/**
			 * Si el usuario tiene una clave predeterminada, le pedirá registrarse.
			 */
			if ("ElorrietaNueva".equalsIgnoreCase(usuario.getPassword())) {
				JsonObject response = new JsonObject();
				response.addProperty("message", "El usuario debe registrarse.");
				client.sendEvent(Events.passwordResetError.value, response);
				return;
			}

			boolean correoEnviado = gestorUsuario.restablecerClave(email);

	        JsonObject response = new JsonObject();
	        if (correoEnviado) {
	            response.addProperty("message", "Correo enviado correctamente.");
	            client.sendEvent(Events.passwordResetSuccess.value, response);
	        } else {
	            response.addProperty("message", "No se pudo enviar el correo. Intenta nuevamente.");
	            client.sendEvent(Events.passwordResetError.value, response);
	        }
		};
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
				List<HorarioDTO> horarios = gestorHorario.getHorarioById(id);

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
