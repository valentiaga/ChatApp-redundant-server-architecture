package serverBackUp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.HashMap;

public class Sincronizacion extends Thread{
	
	private HashMap<String, String> chats;
	private ServerSocket serverSocket;
	private Socket socket;
	private int puerto = 100;
	private ObjectInputStream input;
	
	public Sincronizacion(HashMap<String, String> chats) {
		super();
		this.chats = chats;
	}
	
	public void run(){
		
		try {
			serverSocket = new ServerSocket(puerto);
			socket = serverSocket.accept();
			input = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			try {
				chats = (HashMap<String, String>) input.readObject();
				System.out.println("backup: "+chats);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
