package back;

import java.io.IOException;

public class PingEcho extends Thread {

	private int tiempo = 5; // seconds
	private String mensaje = "PING";
	// private boolean bandera = true;

	@Override
	public void run() {

		while (true) {
		//	while (Conexion.getInstance().getCambiaServer() == false) {

				try {
					Conexion.getInstance().getDos().writeUTF("3" + mensaje);
					Thread.sleep(tiempo * 1000);
					if (Conexion.getInstance().getEcho() == true) {
						System.out.println("ECHO == true");
						Conexion.getInstance().setEcho(false);
					} else {
						//Conexion.getInstance().setCambiaServer(true);
						System.out.println("ECHO == false");
						Conexion.getInstance().cambiaServer();
						//Conexion.getInstance().verificaServer();
					}
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Let the thread sleep for a while.

			}
			
		//}
	}

}
