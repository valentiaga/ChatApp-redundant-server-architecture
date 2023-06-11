package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import server.DataCliente;

public class Server extends Thread {

	private ServerSocket serverSocket;
	private int puerto;
	private static int nro = 0;
	private HashMap<String, DataCliente> clientes = new HashMap<>();
	private HashMap<String, String> chats = new HashMap<>();
	private ArrayList<DataCliente> lista = new ArrayList<DataCliente>();
	private ControladorVistaServer controlador;
	
	private boolean terminar = false;

	
	public Server(int puerto) {
		super();
		this.puerto = puerto + nro;
		nro++;
		System.out.println(nro);
		try {
			System.out.println(puerto);
			this.serverSocket = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Server() {
		super();
		try {
			this.serverSocket = new ServerSocket(puerto);
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
			while (this.terminar == false) {
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
					conection.setCont(controlador);
					conection.start();
					dos.writeUTF("1REGISTRADOCORRECTAMENTE");
					controlador.appendListaConectados(dataCliente.toString());
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

	public ArrayList<DataCliente> getLista() {
		return lista;
	}

	public void setLista(ArrayList<DataCliente> lista) {
		this.lista = lista;
	}

	public void closeServer() throws IOException {	// podriamos cerrar el socket de conexion con otros servidores tmb
		this.terminar = true;
		this.serverSocket.close();
		
	}

	public ControladorVistaServer getControlador() {
		return controlador;
	}

	public void setControlador(ControladorVistaServer controlador) {
		this.controlador = controlador;
	}
	
}