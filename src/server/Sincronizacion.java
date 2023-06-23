package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Sincronizacion extends Thread {

	private Server server;

	private ServerSocket ss;
	private Socket socketMonitor;
	private Socket socketConPrincipal;
	private ArrayList<Socket> listaSocketsServers = new ArrayList<Socket>();

	private int puertoLocal;
	private int puertoPrincipal;
	private int puertoMonitor = 11142;

	private String ipMonitor = "localhost";
	private String ipServerPrincipal;
	private String rol; // PRINCIPAL o SECUNDARIO

	private ConectionMonitor conectionMonitor = null;

	public Sincronizacion(Server server) {
		this.server = server;
		this.conectaMonitor();
	}

	public void conectaMonitor() {
		try {
			socketMonitor = new Socket(ipMonitor, puertoMonitor);

			this.conectionMonitor = new ConectionMonitor(socketMonitor); // escucha al MONITOR
			this.conectionMonitor.start();

			DataInputStream dis = new DataInputStream(socketMonitor.getInputStream());
			// DataOutputStream dos = new DataOutputStream
			// (socketMonitor.getOutputStream());

			this.puertoLocal = Integer.valueOf(dis.readUTF());
			this.ipServerPrincipal = dis.readUTF();
			this.puertoPrincipal = Integer.valueOf(dis.readUTF());
			this.rol = dis.readUTF();

			System.out.println("puerto recibido: " + puertoLocal);

			this.ss = new ServerSocket(puertoLocal);

			if (this.rol.equals("SECUNDARIO")) {
				socketConPrincipal = new Socket(ipServerPrincipal, this.puertoPrincipal);
				server.getControlador().appendMensajes("Sincronizando server respaldo");
				SincronizacionEscucha sinc = new SincronizacionEscucha(socketConPrincipal, this);
			}

////			if (this.puerto != 4444) {
//				Socket socketConPrincipal = new Socket ("localhost", 4444);
////				server.getControlador().appendMensajes("Sincronizando server respaldo");
//				SincronizacionEscucha sinc = new SincronizacionEscucha(socketConPrincipal, this);
////
////			}
			this.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Socket socket = null;

		try {
			while (true) {
				socket = ss.accept();
				this.listaSocketsServers.add(socket);
				System.out.println("Se creo el socket con el principal");
				server.getControlador().appendMensajes("Sincronizando server respaldo");

//				ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());		// como creamos todos los servidores al principio siempre estan vacios los chats
//				dos.writeObject(this.server.getChats());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sincronizaServers() throws IOException {

		if (Server.isPrincipal() == true && Server.isTerminar() == false)
			for (int i = 0; i < this.listaSocketsServers.size(); i++) {
				ObjectOutputStream dos = new ObjectOutputStream(this.listaSocketsServers.get(i).getOutputStream());
				dos.writeObject(this.server.getChats());
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
