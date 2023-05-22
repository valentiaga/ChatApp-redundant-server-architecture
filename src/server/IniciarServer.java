package server;

import java.io.IOException;

public class IniciarServer {

	public static void main(String[] args) {
		Server server = new Server();
		
		try {
			server.Registrar();			// el hilo se va a quedar aca
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} 

}
