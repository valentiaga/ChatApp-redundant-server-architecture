package prueba;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.Cliente;
import server.Server;

public class Prueba {

	public static void main(String[] args) {
	//Server server = new Server();
		
		
		String IP = "localhost";
		int puerto = 2003;
		Socket s;
		Socket s2;
		
		//Cliente cliente = new Cliente("Juancho",123,"1321");
//		ObjectOutputStream dos = null;
//		ObjectOutputStream dos2;
		
		String name = "tuvieja";
		String name2 = "mivieja";
		
		DataInputStream dis = null;
        DataOutputStream dos = null;
        
        DataInputStream dis2 = null;
        DataOutputStream dos2 = null;
        
		String mensaje = "tuvieja";
        String bandera = "1";
        String mensaje2 = "mivieja";
        String bandera2 = "0";
        
		try {
			s = new Socket(IP,puerto);
			
			dis = new DataInputStream(s.getInputStream());
	        dos = new DataOutputStream(s.getOutputStream());
			
			
	        //ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
           // dos = new ObjectOutputStream(s.getOutputStream());
            
            
            //dos.writeObject(name);
	        mensaje = bandera+mensaje;
            dos.writeUTF(mensaje);
	        //dos.writeUTF("tuvieja");
	     
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		 
		 
		 
			try {
				s2 = new Socket(IP,puerto);
				
				dis2 = new DataInputStream(s2.getInputStream());
		        dos2 = new DataOutputStream(s2.getOutputStream());
				
				
		        //ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
	           // dos2 = new ObjectOutputStream(s2.getOutputStream());
	            
	            
	            //dos2.writeObject(name2);
		        
		        mensaje2 = bandera2+mensaje2;
	            dos2.writeUTF(mensaje2);
	            
		        //dos.writeUTF("tuvieja");
		     
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				//System.out.println("Exception");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
	        try {
				dos.writeUTF(mensaje2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        try {
				dos.writeUTF("0holi perdida");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
//	        try {
//				System.out.println("llego"+dis2.readUTF());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	        
	}

}
