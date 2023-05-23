package back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import controladores.ControladorVistaChat;
import controladores.ControladorVistaConecta;
import controladores.ControladorVistaInicial;
import exception.CreaChatException;
import exception.IniciarException;
import exception.UserNotAvailableException;
import front.IVistaChat;

public class Cliente {

	private IVistaChat vistaChat = null;
	private Socket socket;
	private ServerSocket serverSocket;
	private MessageManager messageManager;
	private ConectionHandler conectionHandler = null;
	private ControladorVistaConecta contConecta = null;
	private ControladorVistaChat contChat = null;
	private ControladorVistaInicial contInicial = null;
	
	private String nickname;
	private int puerto;
	private String iP;
	private String contrasena;
	private String error;
	private char comando;
	private int comand;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Cliente() {
	}

	public Cliente(String nickname, int puerto, String iP) throws IOException {
		super();
		this.nickname = nickname;
		this.puerto = puerto;
		this.iP = iP;
		
		socket = new Socket(this.iP, this.puerto);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void conectarServer() throws IOException{

//		Socket s = new Socket(this.iP, this.puerto);
//		dis = new DataInputStream(s.getInputStream());
//		dos = new DataOutputStream(s.getOutputStream());

		dos.writeUTF("1" + this.nickname);
	
//		byte[] texto;
//		String mensaje;
//		mensaje = "1" + this.nickname;
//		texto = mensaje.getBytes();
//    	dos.write(texto);
    	
//		this.messageManager = new MessageManager(s, dis, dos, this.vistaChat);
//		this.recibirMensajes();
	}


	public void recibirMensajes() {
		Socket s = this.messageManager.getSocket();
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {

		}

		this.conectionHandler = new ConectionHandler(s, dis, dos);
		this.conectionHandler.start();
	}

	public void conectarReceptor(String nickNameReceptor)
			throws IOException, UserNotAvailableException, CreaChatException {

		this.messageManager.enviaNickName(nickNameReceptor);
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

	public void setContConecta(ControladorVistaConecta contConecta) {
		this.contConecta = contConecta;
		this.conectionHandler.setContConecta(contConecta);
	}

	public void setContChat(ControladorVistaChat cont) {
		this.contChat = contChat;
		this.conectionHandler.setContChat(cont);

	}
	
	

	public ControladorVistaInicial getContInicial() {
		return contInicial;
	}

	public void setContInicial(ControladorVistaInicial contInicial) {
		this.contInicial = contInicial;
		this.conectionHandler.setContInicial(contInicial);
	}

	public void setConectionHandler(ConectionHandler conectionHandler) {
		this.conectionHandler = conectionHandler;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void creaConectionHandler() {
		this.messageManager = new MessageManager(this.socket, dis, dos, this.vistaChat);
		this.recibirMensajes();
	}

//------------------------------------------------------------------------------------------------------
}
