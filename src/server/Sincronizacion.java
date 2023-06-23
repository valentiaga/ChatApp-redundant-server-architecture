package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Sincronizacion extends Thread{

	private Server server;
	private ServerSocket ss;
	private int puerto;
	
	public Sincronizacion(Server server) {
		this.server = server;
		this.conectaMonitor();
		
	}

	public void conectaMonitor() {
		try {
			Socket socketMonitor = new Socket ("localhost", 11132);
			
			DataInputStream dis = new DataInputStream(socketMonitor.getInputStream());
			//DataOutputStream dos = new DataOutputStream (socketMonitor.getOutputStream());
			
			this.puerto = Integer.valueOf(dis.readUTF());
			System.out.println("puerto recibido: "+puerto);
			this.ss = new ServerSocket (puerto);
			if (this.puerto != 4444) {
				Socket socketConPrincipal = new Socket ("localhost", 4444);
//				server.getControlador().appendMensajes("Sincronizando server respaldo");
				SincronizacionEscucha sinc = new SincronizacionEscucha(socketConPrincipal, this);

			}
			this.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		Socket socket = null;
		
		try {
			while (true) {
				socket = ss.accept();
				System.out.println("Se creo el socket con el principal");
				server.getControlador().appendMensajes("Sincronizando server respaldo");
//				SincronizacionEscucha sinc = new SincronizacionEscucha(socket, this);
//				sinc.start();
//				DataInputStream dis = new DataInputStream(socketMonitor.getInputStream());
				ObjectOutputStream dos = new ObjectOutputStream (socket.getOutputStream());
				
				dos.writeObject(this.server.getChats());
//				server.getControlador().appendMensajes();

			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
	
	
	

}
