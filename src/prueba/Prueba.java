package prueba;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.Cliente;
import back.Conexion;
import server.Server;

public class Prueba {

	public static void main(String[] args) {
		
	try {
		Conexion.getInstance().agregarSocket("localhost",1235);
		//Conexion.getInstance().registrar("Messi");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	        
	}

}
