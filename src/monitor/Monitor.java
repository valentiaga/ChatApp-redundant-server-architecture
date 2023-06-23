package monitor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Monitor extends Thread{
	
	private int puerto=11122;
	private ServerSocket serverSocket;
	private int principal = 0;
	private static int nroSig=0;
	
	public Monitor() throws IOException {
		this.serverSocket = new ServerSocket(this.puerto);
	}
	
	public void run() {
		super.run();
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream (socket.getOutputStream());
				
				if (this.principal == 0) {
					dos.write(4444);
				}
				else {
					dos.write(4445+nroSig);
					nroSig++;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
