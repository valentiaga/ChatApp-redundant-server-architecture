package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sincronizacion {
	
	private static Socket socket;
	private static String ip = "localhost";
	private static int puerto = 101;
	//ObjectInputStream ois;
	private static ObjectOutputStream outPut;
	private static Server server;
	
	public Sincronizacion (Server server) {
		super()	;
		this.server = server;
	}
	
	public static void sincronizarServer(){
		
		try {
			socket = new Socket(ip,puerto);
			outPut = new ObjectOutputStream(socket.getOutputStream());
			
			outPut.writeObject(server.getChats());
//			outPut.writeObject(server.getClientes());
//			outPut.writeObject(server.getListaClientes());
			
			server.getControlador().appendMensajes("Sincronizando server respaldo");
//			dis = new DataInputStream(socket.getInputStream());
//			dos = new DataOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
