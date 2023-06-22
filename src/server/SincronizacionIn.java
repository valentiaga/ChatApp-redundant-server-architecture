package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SincronizacionIn extends Thread {

	private ArrayList<DataCliente> listaClientes;
	private HashMap<String, DataCliente> clientes;
	private HashMap<String, String> chats;

	private Socket socketSiguiente;
	private ServerSocket serverSocket;
	private String ip = "localhost";

	private int puertoActual = 101;
	private int puertoAnterior = 101; // chequear los numero de los puertos

	private ObjectOutputStream outPut;
	private ObjectInputStream input;
	private Server server;

	public SincronizacionIn(Server server) {
		super();
		this.server = server;
		this.chats = server.getChats();
		this.clientes = server.getClientes();
		this.listaClientes = server.getListaClientes();
		//this.socket = socket;
	}

	private void seteaClientes() {

		for (int i = 0; i < this.chats.size(); i++) {
			this.server.getLista().get(i)
					.setNicknameReceptor(this.chats.get(this.server.getLista().get(i).getNickname()));
			System.out.println(this.server.getLista().get(i).getNickname() + ", "
					+ this.server.getLista().get(i).getNicknameReceptor());
		}
	}

	public void run() {
		try {
			// serverSocket = new ServerSocket(puertoActual);
			this.socketSiguiente = this.serverSocket.accept();
			this.server.getControlador().appendMensajes("Socket establecido");
			this.input = new ObjectInputStream(socketSiguiente.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			try {
//				this.listaClientes = (ArrayList<DataCliente>) input.readObject();
//				this.clientes = (HashMap<String, DataCliente>) input.readObject();
				this.chats = (HashMap<String, String>) this.input.readObject();

				this.server.getControlador().appendMensajes("Sincronizando server respaaldo");
				this.server.getControlador().appendMensajes(chats.toString());
				this.seteaClientes();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

}
