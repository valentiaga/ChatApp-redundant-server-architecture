package monitor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HeartBeatMonitor extends Thread {

	private Socket socket;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private boolean terminar = false;
	
	private int tiempo = 5;
	private int intentos = 0;
	
	
	public void run() {

		String comando = null;
		super.run();

//		try {
//			dis = new DataInputStream(socket.getInputStream());
//			dos = new DataOutputStream(socket.getOutputStream());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		while (this.terminar == false) {

			try {
				this.socket = Monitor.getInstance().getSocketPrincipal();
				dis = new DataInputStream(this.socket.getInputStream());
				//dos = new DataOutputStream(this.socket.getOutputStream());
				if (dis.available()>0)
				comando = dis.readUTF();

			} catch (IOException e) {
				
				e.printStackTrace();
			}

			// System.out.println(comando);

			if (comando == "HEARTBEAT") { 
				//ESPERAMOS
				try {
					Thread.sleep(tiempo * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				
				if(this.intentos == 0){ //CAMBIAMOS DE SERVER PRINCIPAL
					
					try {
						Monitor.getInstance().cambiaServerPrincipal();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//ESPERAMOS PARA DAR TIEMPO A QUE SE CAMBIE
					try {
						Thread.sleep(tiempo * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					this.intentos--;
					try {
						Thread.sleep(tiempo * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}

		}
	}


	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	
	
}