package back;

import java.net.Socket;

public class Conexion {
	
	private Socket socket;
	private String nickname;
	
	public Conexion(Socket socket, String nickname) {
		super();
		this.socket = socket;
		this.nickname = nickname;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getNickname() {
		return nickname;
	}
	
	
}
