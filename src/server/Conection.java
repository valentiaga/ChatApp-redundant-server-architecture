package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import front.IVistaChat;

public class Conection extends Thread {
	
	Conexion conexion;
	private HashMap<String,Conexion> clientes;
	
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket socket;
	
    private boolean terminar = false;
    
	public Conection(DataInputStream dis, DataOutputStream dos, Socket s) {
		super();
		this.dis = dis;
		this.dos = dos;
		this.socket = s;
	}

	public Conection(Socket s,Conexion conexion,HashMap<String,Conexion> clientes) throws IOException {
		super();
		this.dis = new DataInputStream(s.getInputStream());
		this.dos = new DataOutputStream(s.getOutputStream());
		this.socket = s;
		this.conexion = conexion;
		this.clientes = clientes;
	}
	
	public Conection(Socket s,Conexion conexion,HashMap<String,Conexion> clientes,DataInputStream dis,DataOutputStream dos) throws IOException {
		super();
		this.dis = dis;
		this.dos = dos;
		this.socket = s;
		this.conexion = conexion;
		this.clientes = clientes;
	}
	
	public void run() {

	//	String recibido;
		super.run();
		String mensaje;
		char bandera;
		
		//while(this.terminar == false && this.s.isClosed() != true) {
		while(this.terminar == false) {
			
			//System.out.println("Hilo");
			try {
				if(dis.available()> 0) {
					//recibido = (String) dis.readObject();
					 mensaje = dis.readUTF();
			         bandera = mensaje.charAt(0);
			         mensaje = mensaje.substring(1);
			         
			         System.out.println(bandera);
			         System.out.println(mensaje);
			         
			        if(bandera == '0') {
			        	if(this.conexion.getNicknameReceptor() != null) {
							this.clientes.get(this.conexion.getNicknameReceptor()).getDos().writeUTF(mensaje);
						}else {
							if(this.clientes.containsKey(mensaje)) {
								System.out.println("Chat");
								this.conexion.setNicknameReceptor(mensaje);
								this.clientes.get(mensaje).setNicknameReceptor(this.conexion.getNickname());
								// INICIAR LOS CHATS EN LOS CLIENTES
							}else { 
								// no esta registrado ese nickname de receptor
							}
						}
					} else {
						if (bandera == '1') {	// comando para el servidor
							
						}
					}
				}
				
			} 
			catch (EOFException e) {
				//e.printStackTrace();
				this.terminarRecibirMensajes();
			}
			catch (SocketException e1) {
				this.terminarRecibirMensajes();
				//e1.printStackTrace();
			}
			catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void terminarRecibirMensajes() {
		//String mensaje = "El otro usuario se desconecto.\n";
		this.terminar = true;
		
		//this.vista.getTextArea().setText(this.vista.getTextArea().getText()+"\n"+mensaje);
		try {
			this.dis.close();
			this.dos.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
