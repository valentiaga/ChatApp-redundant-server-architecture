package prueba;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import back.Server;

public class Prueba {

	public static void main(String[] args) {
	Server server = new Server();
		
		
		String IP = "localhost";
		Socket s;
		try {
			s = new Socket(IP,1234);
			
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
	        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	        dos.writeUTF("tuvieja");
	        server.Conectar();
	        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 Socket s2;
	        try {
	            s2 = new Socket(IP,1234);

	            DataInputStream dis2 = new DataInputStream(s2.getInputStream());
	            DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());
	            dos2.writeUTF("miviejaahr");
	            server.Conectar();

	        } catch (UnknownHostException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        

	}

}
