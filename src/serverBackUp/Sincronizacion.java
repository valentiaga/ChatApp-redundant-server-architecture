package serverBackUp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Sincronizacion extends Thread{
	
	private ArrayList<DataCliente> listaClientes;
	private HashMap<String, DataCliente> clientes;
	private HashMap<String, String> chats;
	private ServerSocket serverSocket;
	private Socket socket;
	private int puerto = 101;
	private ObjectInputStream input;
	private Server server;
	
	
	public Sincronizacion(Server server) {
		super();
		this.chats = server.getChats();
		this.server = server;
		this.clientes = server.getClientes();
		this.listaClientes = server.getListaClientes();
	}
	
	public void run(){
		
		try {
			serverSocket = new ServerSocket(puerto);
			socket = serverSocket.accept();
			this.server.getControlador().appendMensajes("Socket establecido");
			input = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			try {
				this.listaClientes = (ArrayList<DataCliente>) input.readObject();
				this.clientes = (HashMap<String, DataCliente>) input.readObject();
				chats = (HashMap<String, String>) input.readObject();
				this.server.getControlador().appendMensajes("Sincronizando server respaaldo");
				this.server.getControlador().appendMensajes(chats.toString());
				this.seteaClientes();
				
				//System.out.println("backup: "+chats);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void seteaClientes() {
		
		for(int i = 0; i < this.chats.size();i++) {
			this.server.getLista().get(i).setNicknameReceptor(this.chats.get(this.server.getLista().get(i).getNickname()));
		}
	}
}
