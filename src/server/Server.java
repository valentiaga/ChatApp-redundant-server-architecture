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
	private int puerto = 200;

	private HashMap<String, DataCliente> clientes = new HashMap<>();
	private HashMap<String, String> chats = new HashMap<>();
	private ArrayList<DataCliente> lista = new ArrayList<DataCliente>();

	private boolean terminar = false;

	public Server() {
		super();
		try {
			this.serverSocket = new ServerSocket(this.puerto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// System.out.println("Constructor");
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
					//dos.writeUTF("1TRUE");
					Conection conection = new Conection(s, dataCliente, this.clientes, dis, dos);
					conection.start();

					System.out.println("Clientes en Server" + this.clientes);
				} else {
					System.out.println("Usuario ya registrado" + nickname);
					System.out.println("Clientes en Server" + this.clientes);
					dos.writeUTF("1USERREGISTRADO");
				}
			}

		} catch (SocketException e) {
			// serverSocket.close();
			e.printStackTrace();
		} catch (Exception e1) {
			// serverSocket.close();
			e1.printStackTrace();
		}

	}

	// nickname del usuario con el que se va a conectar
//	public void creaChat(String nickname, String nicknameReceptor) {
//
//		if (this.clientes.containsKey(nicknameReceptor)) {
//			this.chats.put(nickname, nicknameReceptor);
//			this.chats.put(nicknameReceptor, nickname);
//			System.out.println("Crea chat");
//		} else {
//			// no se pudo crear el chat porque el nickname no esta registrado
//			System.out.println("nombre no registrado"); // deberiamos mandar por el socket una exception a la ventana
//		}
//	}



//	private void enviarAlReceptor() {
//
//		String recibido;
//		super.run();
//		String mensaje = "Socket closed";
//		String name, receptor;
//		int i;
//		Socket socket;
//		DataInputStream dis;
//		DataOutputStream dos;
//
//		i = 0;
//		while (i < this.lista.size()) {
//			socket = this.lista.get(i).getSocket();
//			name = this.lista.get(i).getNickname();
//			receptor = this.lista.get(i).getNicknameReceptor();
//
//			try {
//				dis = new DataInputStream(socket.getInputStream());
//				// dos = new DataOutputStream(socket.getOutputStream());
//				recibido = dis.readUTF();
//
//				if (recibido != null && receptor != null) {
//					dos = new DataOutputStream(this.clientes.get(receptor).getSocket().getOutputStream());
//					dos.writeUTF(recibido);
//				} else {
//					// no hay un receptor, no hay chat
//				}
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}



}
