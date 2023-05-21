package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import exception.CreaChatException;
import exception.IniciarException;
import exception.UserNotAvailableException;
import front.IVistaChat;

public class Cliente {

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
	private String error;
	private char comando;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Cliente() {
	}

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

	public void conectarServer() throws UnknownHostException, IOException, IniciarException {

		Socket s = new Socket(this.iP, this.puerto);
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());

		dos.writeUTF("0" + this.nickname);
		if (dis.available() > 0) {
			error = dis.readUTF();
			comando = error.charAt(0);
			error = error.substring(1);
			if (comando == '2')
				throw new IniciarException(error);
		} else
			this.messageManager = new MessageManager(s, dis, dos, this.vistaChat);
	}

//------------------------------------------------------------------------------------------------------
//		public void Conectar(final int puerto) throws IOException {
//			
//			 ServerSocket ss = new ServerSocket(puerto);
//
//		            Socket s = null;
//		              
//		            try 
//		            {
//		                s = ss.accept();
//		                
//		               // System.out.println(s.isConnected());
//		                 
//		                DataInputStream dis = new DataInputStream(s.getInputStream());
//		                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//		                  
//		                this.socket = s;
//		                this.messageManager = new MessageManager(s, dis, dos,this.vistaChat);
//		                this.conectionHandler = new ConectionHandler(s, dis, dos,this.vistaChat);
//		                
//		                this.conectionHandler.start();
//		                  
//		            }
//		            catch (Exception e){
//		                s.close();
//		                ss.close();
//		                e.printStackTrace();
//		            }
//		       
//		    }

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

		this.conectionHandler = new ConectionHandler(s, dis, dos, this.vistaChat);
		this.conectionHandler.start();
	}

	public void conectarReceptor(String nickNameReceptor) throws IOException, UserNotAvailableException, CreaChatException {

		this.messageManager.enviaMensaje("0" + nickNameReceptor);
		error = dis.readUTF();
		comando = error.charAt(0);
		error = error.substring(1);

		if (comando == '2') {
			throw new UserNotAvailableException(
					"El usuario con el que desea comunicarse no está registrado en el sistema.");
		}
		else
			if (comando == '1') {
				throw new CreaChatException();
			}
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

//------------------------------------------------------------------------------------------------------
}
