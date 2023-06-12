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
	private SendMessage sendMessage;
	private ReceiveMessage receiveMessage = null;
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
		
		// esto va en Conexion
		Conexion.getInstance().setCliente(this);
		Conexion.getInstance().agregarSocket(iP, puerto);
		Conexion.getInstance().agregarSocket(iP, puerto+1);
//		socket = new Socket(this.iP, this.puerto);
//		dis = new DataInputStream(socket.getInputStream());
//		dos = new DataOutputStream(socket.getOutputStream());
		
	}

	public void conectarServer() throws IOException{
		
//		Conexion.getInstance().getDos().writeUTF("1" + this.nickname);
		Conexion.getInstance().registrar(this.nickname);
		PingEcho pingEcho = new PingEcho();
		pingEcho.start();
		
//		dos.writeUTF("1" + this.nickname);
		
	}


	public void recibirMensajes() {
//		Socket s = this.messageManager.getSocket();
//		DataInputStream dis = null;
//		DataOutputStream dos = null;
//		try {
//			dis = new DataInputStream(s.getInputStream());
//			dos = new DataOutputStream(s.getOutputStream());
//		} catch (IOException e) {
//
//		}

		//this.conectionHandler = new ReceiveMessage(s, dis, dos);
		this.receiveMessage = new ReceiveMessage();
		this.receiveMessage.start();
	}

	public void conectarReceptor(String nickNameReceptor)
			throws IOException, UserNotAvailableException, CreaChatException {

		this.sendMessage.enviaNickName(nickNameReceptor);
	}

	public SendMessage getMessageManager() {
		return this.sendMessage;
	}

	public void setVista(IVistaChat v) {
		this.vistaChat = v;
	}

//	public Socket getsocket() {
//		return this.socket;
//	}

	public ReceiveMessage getReceiveMessage() {
		return receiveMessage;
	}

	public void setContConecta(ControladorVistaConecta contConecta) {
		this.contConecta = contConecta;
		this.receiveMessage.setContConecta(contConecta);
	}

	public void setContChat(ControladorVistaChat cont) {
		this.contChat = contChat;
		this.receiveMessage.setContChat(cont);

	}
	

	public ControladorVistaInicial getContInicial() {
		return contInicial;
	}

	public void setContInicial(ControladorVistaInicial contInicial) {
		this.contInicial = contInicial;
		this.receiveMessage.setContInicial(contInicial);
	}

	public void setReceiveMessage(ReceiveMessage conectionHandler) {
		this.receiveMessage = conectionHandler;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void creaConectionHandler() {
		//this.messageManager = new SendMessage(this.socket, dis, dos, this.vistaChat);
		this.sendMessage = new SendMessage();
		this.recibirMensajes();
	}

//------------------------------------------------------------------------------------------------------
}
