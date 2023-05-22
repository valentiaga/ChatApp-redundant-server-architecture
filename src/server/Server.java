package server;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import UI.IVistaChat;

public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private int puerto = 115; 
//	private DataInputStream dis;
//    private DataOutputStream dos;
	//hasmap de nickname con socket
    //final Socket s;
	private HashMap<String,DataCliente> clientes = new HashMap<>();
	private HashMap<String,String> chats = new HashMap<>();
	private ArrayList<DataCliente> lista = new ArrayList<DataCliente>(); 
	
    private boolean terminar = false;		// creo que el servidor no tiene que cortar
    
    
	public Server() {
		super();
		try {
			this.serverSocket = new ServerSocket(this.puerto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Constructor");
			e.printStackTrace();
		}
	}
	 
	public void Registrar() throws IOException {
		
	            Socket s = null;
	            String nickname; 
	            String nicknameReceptor;
	            DataCliente conexion;
	            Object object;
	            char bandera;
	             
	            try 
	            {
	            	while(true) {
	            		s = serverSocket.accept();
		                
	 	               // System.out.println(s.isConnected());
	 	                 
	 	                DataInputStream dis = new DataInputStream(s.getInputStream());
	 	                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	            		
	 	                
	 	              nickname = dis.readUTF();
	 	              bandera = nickname.charAt(0);
	 	              nickname = nickname.substring(1);
	 	              
	 	              //System.out.println(nickname);
	 	              
	 	               
	 	              	if(this.clientes.containsKey(nickname) == false) {
	 	              		conexion = new DataCliente(s,nickname,dis,dos);
		 	                this.clientes.put(nickname,conexion);
		 	                this.lista.add(conexion);
		 	                Conection conection = new Conection(s,conexion,this.clientes,dis,dos);
		 	                conection.start();						// capaz podriamos poner en una coleccion los hilos, no se pa que
		 	                
		 	                System.out.println("Clientes en Server" +this.clientes);
	 	              	}
	 	              	else {
	 	              		System.out.println("Aca si entre");
	 	              		dos.writeUTF("2Ingrese un nombre de usuario que no esté registrado en el sistema.");
	 	              	}
	 	                
	 	                
	 	                
	 	                //nicknameReceptor = dis.readUTF();
	 	                //this.chats.put(nickname, dis.readUTF());
	 	                
	 	                
	 	                //System.out.println(nickname);
	 	                
	 	                //dis.readUTF();
	 	                //this.socket = s;
//	 	                this.messageManager = new MessageManager(s, dis, dos,this.vistaChat);
//	 	                this.conectionHandler = new ConectionHandler(s, dis, dos,this.vistaChat);
	 	                
	 	                //this.conectionHandler.start();
	 	                  
	            	}
	            	
	            } catch (SocketException e){
	                //serverSocket.close();
	                e.printStackTrace();
	            }catch (Exception e1){
	                //serverSocket.close();
	                e1.printStackTrace();
	            }
//	            if(s != null)
//	            	s.close();
	          
	    }
    
	//nickname del usuario con el que se va a conectar
	public void creaChat(String nickname,String nicknameReceptor) {
		
		if(this.clientes.containsKey(nicknameReceptor)) {
			this.chats.put(nickname, nicknameReceptor);
			this.chats.put(nicknameReceptor, nickname);
			System.out.println("Crea chat");
		}
		else {
			// no se pudo crear el chat porque el nickname no esta registrado
			System.out.println("nombre no registrado");			// deberiamos mandar por el socket una exception a la ventana
		}
	}
	
//	public void run() {
//
//		String recibido;
//		super.run();
//		//String mensaje = "Socket closed";
//		String name,receptor;
//		int i;
//		Socket socket;
////		DataInputStream dis;
////        DataOutputStream dos;
//        ObjectInputStream dis;
//        ObjectOutputStream dos;
//        
//         
//		//while(this.terminar == false && this.s.isClosed() != true) {
//		while(this.terminar == false) {
//			//System.out.println("entro");
//			i=0;
//			System.out.println(this.lista);
//			while(i < this.lista.size()) {
//				
//				socket = this.lista.get(i).getSocket();
//				name = this.lista.get(i).getNickname();
//				receptor = this.lista.get(i).getNicknameReceptor();
//				System.out.println(socket);
//				
//				try {
//					//dis = new DataInputStream(socket.getInputStream());
//					dis = new ObjectInputStream(socket.getInputStream());
//					if(dis.available()> 0) {
//						recibido = (String) dis.readObject();
//					//recibido = dis.readUTF();
//					
//					
//					//if(recibido != null){ 
//						if(receptor != null){
//							
////							dos = new DataOutputStream(this.clientes.get(receptor).getSocket().getOutputStream()); 
//							//dos.writeUTF(recibido);
//							dos = new ObjectOutputStream(this.clientes.get(receptor).getSocket().getOutputStream()); 
//							
//							dos.writeObject(recibido);
//							
//						}else {					// no hay receptor, hay que iniciar el chat
//							this.creaChat(name, receptor);
//							//this.creaChat(receptor, name);
//						}
//					//}else {}
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				i++;
//			}
			
//			try {
//				recibido = dis.readUTF();
//				// enviar al cliente
//				
//			} 
//			catch (EOFException e) {
//				//e.printStackTrace();
//				this.terminarRecibirMensajes();
//			}
//			catch (SocketException e1) {
//				this.terminarRecibirMensajes();
//				//e1.printStackTrace();
//			}
//			catch (IOException e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
	
	private void enviarAlReceptor() {
		
		String recibido;
		super.run();
		String mensaje = "Socket closed";
		String name,receptor;
		int i;
		Socket socket;
		DataInputStream dis;
        DataOutputStream dos;
        
        i=0;
		while(i < this.lista.size()) {
			socket = this.lista.get(i).getSocket();
			name = this.lista.get(i).getNickname();
			receptor = this.lista.get(i).getNicknameReceptor();
			
			try {
				dis = new DataInputStream(socket.getInputStream());
				//dos = new DataOutputStream(socket.getOutputStream());
				recibido = dis.readUTF();
				
				if(recibido != null && receptor != null) {
					dos = new DataOutputStream(this.clientes.get(receptor).getSocket().getOutputStream()); 
					dos.writeUTF(recibido);
				}else {
					// no hay un receptor, no hay chat
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	public void terminarRecibirMensajes() {
//		//String mensaje = "El otro usuario se desconecto.\n";
//		this.terminar = true;
//		
//		//this.vista.getTextArea().setText(this.vista.getTextArea().getText()+"\n"+mensaje);
//		try {
//			this.dis.close();
//			this.dos.close();
//			this.s.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	

	
}
