package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConectionMonitor extends Thread {

	private Socket socketMonitor;
	private String comando;
	
	
	
	public ConectionMonitor(Socket socketMonitor) {
		this.socketMonitor = socketMonitor;
	}


	public void run() {

		DataInputStream dis = null;
		try {
			dis = new DataInputStream(socketMonitor.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (Server.isTerminar() == false) {
			try {
				comando = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(comando.equals("PRINCIPAL")) {
				Server.setPrincipal(true);
			}
		}
	}
}
