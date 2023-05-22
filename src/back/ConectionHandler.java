package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import controladores.ControladorVistaChat;
import controladores.ControladorVistaConecta;


public class ConectionHandler extends Thread 
{
	ControladorVistaChat contChat=null;
	ControladorVistaConecta contConecta=null;
	final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    private boolean terminar = false;
    
	public ConectionHandler(Socket s,DataInputStream dis, DataOutputStream dos) {
		super();
		this.dis = dis;
		this.dos = dos;
		this.s = s; 
	}

	public void run(){ 
		
		
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
	            	//this.vista.getTextArea().setText(this.vista.getTextArea().getText()+"\n"+mensaje);
	            }else {
	            	if(comando == '1') {
	            		switch (mensaje) {
	            		case "INICIARCHAT":
	            			this.contConecta.iniciaChat();
	            		break;
	            		case "NOREGISTRADO":
	            			this.contConecta.ventanaEmergente("El usuario no se encuentra reistrado en el sistema.");
		            	break;
	            		case "FINALIZACHAT":
	            			
		            	break;
	            		case "USERREGISTRADO":
	            			this.contConecta.ventanaEmergente("El usuario ya se encuentra registrado en el sistema.");
	            		break;
	            		}
	
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

	public ControladorVistaChat getContChat() {
		return contChat;
	}

	public void setContChat(ControladorVistaChat contChat) {
		this.contChat = contChat;
	}

	public ControladorVistaConecta getContConecta() {
		return contConecta;
	}

	public void setContConecta(ControladorVistaConecta contConecta) {
		this.contConecta = contConecta;
	}
	
	
	
    
}


