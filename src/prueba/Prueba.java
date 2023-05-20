package prueba;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import server.Server;

public class Prueba {

	public static void main(String[] args) {
	//Server server = new Server();
		
		
		String IP = "localhost";
		Socket s;
		try {
			s = new Socket(IP,1234);
			
//			DataInputStream dis = new DataInputStream(s.getInputStream());
//	        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	        
	        //ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
	        dos.writeUTF("tuvieja");
	     
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		 Socket s2;
//	        try {
//	            s2 = new Socket(IP,1234);
//
////	            DataInputStream dis2 = new DataInputStream(s2.getInputStream());
////	            DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());
//	            
//	            ObjectInputStream dis2 = new ObjectInputStream(s2.getInputStream());
//	            ObjectOutputStream dos2 = new ObjectOutputStream(s2.getOutputStream());
//	            dos2.writeUTF("miviejaahr");
//	           
//
//	        } catch (UnknownHostException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }
	        

	}

}
