package serverBackUp;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DataCliente {
	
	private Socket socket;
	private String nickname;
	private String nicknameReceptor;
	final DataInputStream dis;
	final DataOutputStream dos;
	
	public DataCliente(Socket socket, String nickname,String nicknameReceptor) throws IOException {
		super();
		this.socket = socket;
		this.nickname = nickname;
		this.nicknameReceptor = nicknameReceptor;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	
	public DataCliente(Socket socket, String nickname) throws IOException {
		super();
		this.socket = socket;
		this.nickname = nickname;
		this.nicknameReceptor = null;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	public DataCliente(Socket socket, String nickname,DataInputStream dis,DataOutputStream dos) throws IOException {
		super();
		this.socket = socket;
		this.nickname = nickname;
		this.nicknameReceptor = null;
		this.dis = dis;
		this.dos = dos;
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

	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		return this.dos;
	}

	@Override
	public String toString() {
		return nickname+socket.getLocalPort();
	}
	
	
	
}
