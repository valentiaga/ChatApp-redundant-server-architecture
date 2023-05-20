package server;

import java.io.IOException;

public class IniciarServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		
		try {
			server.Registrar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.start();
	}

}
