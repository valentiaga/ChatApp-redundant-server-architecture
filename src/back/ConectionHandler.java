package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import exception.UserNotAvailableException;
import front.IVistaChat;


public class ConectionHandler extends Thread 
{
	IVistaChat vista;
	final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    private boolean terminar = false;
    
	public ConectionHandler(Socket s,DataInputStream dis, DataOutputStream dos,IVistaChat vista) {
		super();
		this.vista = vista;
		this.dis = dis;
		this.dos = dos;
		this.s = s;
	}

	public void run(){
		
		String INICIARCHAT = "INICIARCHAT";
		String NOREGISTRADO = "NOREGISTRADO";
		String FINALIZACHAT = "FINALIZACHAT";
		
		String mensaje;
		char comando;
		super.run();
		
		
		//while(this.terminar == false && this.s.isClosed() != true) {
		while(this.terminar == false) {
			try {
				  mensaje = dis.readUTF();
	              comando = mensaje.charAt(0); 
	              mensaje = mensaje.substring(1);
	            
	              System.out.println("CONECTIONHANDLERRRRR");
	              
	            if(comando == '0') {	// mensaje
	            	this.vista.getTextArea().setText(this.vista.getTextArea().getText()+"\n"+mensaje);
	            }else {
	            	if(comando == '1') {
//	            		switch (mensaje) {
//	            		case "INICIARCHAT":
//	            			
//	            		break;
//	            		case "NOREGISTRADO":
//	            			
//		            	break;
//	            		case "FINALIZACHAT":
//	            			
//		            		break;
//	            		}
	 
	            	}
	            }
				
			} 
			catch (EOFException e) {
				//e.printStackTrace();
				this.terminarRecibirMensajes();
			}
			catch (SocketException e1) {
				this.terminarRecibirMensajes();
				//e1.printStackTrace();
			}
			catch (IOException e2) {
				e2.printStackTrace();
			} 
		}
	}
	
	public void terminarRecibirMensajes() {
		//String mensaje = "El otro usuario se desconecto.\n";
		this.terminar = true;
		
		//this.vista.getTextArea().setText(this.vista.getTextArea().getText()+"\n"+mensaje);
		try {
			this.dis.close();
			this.dos.close();
			this.s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
    
}


