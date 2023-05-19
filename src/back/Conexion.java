package back;

import java.net.Socket;

public class Conexion {
	
	private Socket socket;
	private String nickname;
	private String nicknameReceptor;
	
	public Conexion(Socket socket, String nickname) {
		super();
		this.socket = socket;
		this.nickname = nickname;
		this.nicknameReceptor = null;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getNickname() {
		return nickname;
	}

	public String getNicknameReceptor() {
		return nicknameReceptor;
	}

	public void setNicknameReceptor(String nicknameReceptor) {
		this.nicknameReceptor = nicknameReceptor;
	}
	
	
}
