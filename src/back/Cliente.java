package back;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import UI.IVistaChat;

public class Cliente 
{
	
	private IVistaChat vistaChat = null;
	private Socket socket;
	private ServerSocket serverSocket;
//	private PrintWriter out;
//	private BufferedReader in;
	private MessageManager messageManager;
	private ConectionHandler conectionHandler = null;
	
	private String nickname;
	private int puerto;
	private String iP;
	private String contrasena;
	
	public Cliente() {}
	 
	public Cliente(String nickname, int puerto, String iP) {
		super();
		this.nickname = nickname;
		this.puerto = puerto;
		this.iP = iP;
	}
	
//	public Conexion(IVistaChat vistaChat) {
//		super();
//		this.vistaChat = vistaChat;
//	}

	
	
		public void Conectar(final int puerto) throws IOException {
			
			 ServerSocket ss = new ServerSocket(puerto);

		            Socket s = null;
		              
		            try 
		            {
		                s = ss.accept();
		                
		               // System.out.println(s.isConnected());
		                 
		                DataInputStream dis = new DataInputStream(s.getInputStream());
		                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		                  
		                this.socket = s;
		                this.messageManager = new MessageManager(s, dis, dos,this.vistaChat);
		                this.conectionHandler = new ConectionHandler(s, dis, dos,this.vistaChat);
		                
		                this.conectionHandler.start();
		                  
		            }
		            catch (Exception e){
		                s.close();
		                ss.close();
		                e.printStackTrace();
		            }
		       
		    }

	
		public void recibirMensajes() {
			Socket s = this.messageManager.getSocket();
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            this.conectionHandler = new ConectionHandler(s, dis, dos,this.vistaChat);
            this.conectionHandler.start();
		}
		
		public void conectarServer(String IP, int puerto) throws UnknownHostException, IOException {
			
			Socket s = new Socket(IP,puerto);
			DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
            dos.writeUTF(this.nickname);
			this.messageManager  = new MessageManager(s,dis,dos,this.vistaChat);
		}
		
		
		public MessageManager getMessageManager() {
			return this.messageManager;
		}

		public void setVista(IVistaChat v) {
			this.vistaChat = v;
		}
		 
		public Socket getsocket() {
			return this.socket;
		}

		public ConectionHandler getConectionHandler() {
			return conectionHandler;
		}
		
		
}
