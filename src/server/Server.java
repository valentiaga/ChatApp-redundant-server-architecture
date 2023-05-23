package server;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread {

	private ServerSocket serverSocket;
	private int puerto = 5634;

	private HashMap<String, DataCliente> clientes = new HashMap<>();
	private HashMap<String, String> chats = new HashMap<>();
	private ArrayList<DataCliente> lista = new ArrayList<DataCliente>();

	private boolean terminar = false;

	public Server() {
		super();
		try {
			this.serverSocket = new ServerSocket(this.puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Registrar() throws IOException {

		Socket s = null;
		String nickname;
		String nicknameReceptor;
		DataCliente dataCliente;
		Object object;
		char bandera;

		try {
			while (true) {
				s = serverSocket.accept();

				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				nickname = dis.readUTF();
				bandera = nickname.charAt(0);
				nickname = nickname.substring(1);

				if (this.clientes.containsKey(nickname) == false) {
					dataCliente = new DataCliente(s, nickname, dis, dos);
					this.clientes.put(nickname, dataCliente);
					this.lista.add(dataCliente);

					Conection conection = new Conection(s, dataCliente, this.clientes, dis, dos);
					conection.start();
					dos.writeUTF("1REGISTRADOCORRECTAMENTE");
				} else {

					dos.writeUTF("1USERREGISTRADO");
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}