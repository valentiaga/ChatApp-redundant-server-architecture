package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import front.IVistaChat;


public class MessageManager {
	
	IVistaChat vista;
	final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    //podemos tener aca el nickname del usuario al que le debe llegar el mensaje
    //mejor si esta en el server
    
	public MessageManager(Socket s,DataInputStream dis, DataOutputStream dos,IVistaChat vista) {
		super();
		this.vista = vista;
		this.dis = dis;
		this.dos = dos;
		this.s = s;
	}
    
    public void enviaMensaje(String mensaje) {
    	
    	if(this.s.isClosed() != true) {
    		try {
    			this.dos.writeUTF(mensaje);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
    
    Socket getSocket() {
    	return this.s;
    }
}
