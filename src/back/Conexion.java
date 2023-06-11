package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Conexion {
	
	public  static Conexion instance = null;
	private static Socket s;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	
	// esto puede ser un arreglo de sockets
	private Socket s2;
	private DataInputStream dis2;
	private DataOutputStream dos2;
    
	private Conexion(Socket s, DataInputStream dis, DataOutputStream dos) {
	
			super();
			this.s = s;
			this.dis = dis;
			this.dos = dos;
	}
	
	public static Conexion getInstance() {
		
		if(instance == null) {
			s = s;
			dis = dis;
			dos = dos;
		}
			return instance;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		this.verificaServer();
	
		return dos;
	}
    
	private boolean verificaServer() {
		//cosillas
		return true;
	}
	
	private void cambiaServer() {
		s = this.s2;
		dis = this.dis2;
		dos = this.dos2;
	}
	
   
}
