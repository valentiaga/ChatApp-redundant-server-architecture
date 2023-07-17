package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import server.DataCliente;

public class Server extends Thread {

	private ServerSocket serverSocketCliente;
	private Socket socketMonitor;
	public int puerto;
	private HashMap<String, DataCliente> clientes = new HashMap<>();
	private HashMap<String, String> chats = new HashMap<>();
	private ArrayList<DataCliente> listaClientes = new ArrayList<DataCliente>();
	private ControladorVistaServer controlador;
	private static boolean terminar = false;
	private static boolean principal = false;
	private Sincronizacion sincronizacion;
	private Socket s = null;

	
	//-----------SINCRONIZACION-------------------------
	
	private int puertoLocal;
	private int puertoPrincipal;
	private int puertoMonitor = 11304;
	
	private String ipMonitor = "localhost";
	private String ipServerPrincipal;
	private String rol; // PRINCIPAL o SECUNDARIO
	
	//-------------------------------------------------
	public Server(String text, ControladorVistaServer cont) {
		this.puerto = Integer.parseInt(text);

		try {
			this.serverSocketCliente = new ServerSocket(puerto);
			this.controlador = cont;
			this.controlador.setServer(this);
			this.sincronizacion = new Sincronizacion(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String nickname;
		String nicknameReceptor;
		DataCliente dataCliente;
		Object object;
		char bandera;

		try {
			while (this.terminar == false) {
				s = serverSocketCliente.accept();
				// i++;
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				nickname = dis.readUTF();
				bandera = nickname.charAt(0);
				nickname = nickname.substring(1);

				if (this.clientes.containsKey(nickname) == false) {
					dataCliente = new DataCliente(s, nickname, dis, dos);
					this.clientes.put(nickname, dataCliente);
					this.listaClientes.add(dataCliente);

//					ConectionCliente conection = new ConectionCliente(s, dataCliente.getNickname(), this.clientes, dis,
//							dos, this.chats, this.sincronizacion);
//					conection.setCont(controlador);
					// conection.start();
					this.conectarCliente(dataCliente, controlador, dis);

					dos.writeUTF("1REGISTRADOCORRECTAMENTE");
					controlador.appendListaConectados(dataCliente.toString());

				} else {

					dos.writeUTF("1USERREGISTRADO");
				}
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public ArrayList<DataCliente> getLista() {
		return listaClientes;
	}

	public HashMap<String, DataCliente> getClientes() {
		return clientes;
	}

	public void setLista(ArrayList<DataCliente> lista) {
		this.listaClientes = lista;
	}

	public void closeServer() throws IOException { // podriamos cerrar el socket de conexion con otros servidores tmb
		this.terminar = true;
		this.principal = false;
//		this.s.close();
	}

	public HashMap<String, String> getChats() {
		return chats;
	}

	public ControladorVistaServer getControlador() {
		return controlador;
	}

	public void setControlador(ControladorVistaServer controlador) {
		this.controlador = controlador;
	}

	public ArrayList<DataCliente> getListaClientes() {
		return listaClientes;
	}

	public void setServerSocketCliente(ServerSocket serverSocketCliente) {
		this.serverSocketCliente = serverSocketCliente;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public void setClientes(HashMap<String, DataCliente> clientes) {
		this.clientes = clientes;
	}

	public void setChats(HashMap<String, String> chats) {
		this.chats = chats;
	}

	public void setListaClientes(ArrayList<DataCliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public static void setTerminar(boolean terminar) {
		Server.terminar = terminar;
	}

	public static void setPrincipal(boolean principal) {
		Server.principal = principal;
	}

	public void setSincronizacion(Sincronizacion sincronizacion) {
		this.sincronizacion = sincronizacion;
	}

	public static boolean isPrincipal() {
		return principal;
	}

	public static boolean isTerminar() {
		return terminar;
	}

//------------------------------CONECTION CLIENTE ---------------------------------------------
	public void conectarCliente(DataCliente dataCliente, ControladorVistaServer cont, DataInputStream dis) {
		new Thread(() -> {
			String comando;
			String mensaje;
			char bandera;

			while (this.terminar == false) {

				try {
					if (dis.available() > 0) {

						mensaje = dis.readUTF();
						bandera = mensaje.charAt(0);
						mensaje = mensaje.substring(1);

						if (bandera == '0') {
							cont.appendMensajes("receptor: " + dataCliente.getNicknameReceptor());
							if (dataCliente.getNicknameReceptor() != null) {
								cont.appendMensajes(dataCliente.getNickname() + " para "
										+ dataCliente.getNicknameReceptor() + ":" + mensaje);
								System.out.println(
										"envia server " + this.clientes.get(dataCliente.getNicknameReceptor()));

								this.clientes.get(dataCliente.getNicknameReceptor()).getDos().writeUTF("0" + mensaje);
								this.clientes.get(dataCliente.getNicknameReceptor()).getDos().flush();
							}
						} else {
							if (bandera == '1') { // comando para el servidor

								if (mensaje.equals("FINALIZARCHAT") == true) {
									cont.appendMensajes(dataCliente.getNickname() + " finalizó el chat con "
											+ dataCliente.getNicknameReceptor());

									this.clientes.get(dataCliente.getNicknameReceptor()).getDos()
											.writeUTF("1" + mensaje);
									this.clientes.get(dataCliente.getNicknameReceptor()).setNicknameReceptor(null);
									dataCliente.setNicknameReceptor(null);

								}
							} else {
								if (bandera == '2') {
									if ((mensaje.equals(dataCliente.getNickname())) == false
											&& this.clientes.containsKey(mensaje)
											&& this.clientes.get(mensaje).getNicknameReceptor() == null) {

										dataCliente.setNicknameReceptor(mensaje);
										this.clientes.get(mensaje).setNicknameReceptor(dataCliente.getNickname());

										// seteamos los chats
										this.chats.put(dataCliente.getNickname(), mensaje);
										this.chats.put(mensaje, dataCliente.getNickname());
										// this.sincronizacion.sincronizaServers();

										comando = "1INICIARCHAT";
										dataCliente.getDos().writeUTF(comando);
										dataCliente.getDos().flush();

										this.clientes.get(mensaje).getDos().writeUTF(comando);
										this.clientes.get(mensaje).getDos().flush();
										cont.appendMensajes(dataCliente.getNickname() + " inició un chat con "
												+ dataCliente.getNicknameReceptor());

									} else {
										// no esta registrado el receptor
										comando = "1NOREGISTRADO";
										dataCliente.getDos().writeUTF(comando);

									}
								}
//								
							}
						}

					}

				} catch (EOFException e) {
					this.terminarRecibirMensajes();
				} catch (SocketException e1) {
					this.terminarRecibirMensajes();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}

		}).start();
	}

	public void terminarRecibirMensajes() {

		terminar = true;
	}

	// ------------------------------CONECTION MONITOR ---------------------------------------------
	public void conectarMonitor() {
		new Thread(() -> {
			DataInputStream dis = null;
			String comando = null;
			try {
				dis = new DataInputStream(socketMonitor.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}

			while (Server.isTerminar() == false) {
				try {
					comando = dis.readUTF();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (comando.equals("PRINCIPAL")) {
					System.out.println("Server principal recibido");
					this.iniciaHeartBeat();
					Server.setPrincipal(true);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					this.sincronizacion.getSinc().seteaClientes();
				} else if (comando.equals("NUEVO_PUERTO")) {
					try {
						System.out.println("nuevo puerto recibido");
						this.sincronizacion.conectarConPrincipal();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void iniciaHeartBeat() {
//		this.heartBeat = new HeartBeatServer(this.socketMonitor);
//		this.heartBeat.iniciarHilo();
//		this.sincronizacion.sincronizaServers();
	}
	// ------------------------------SINCRONIZACION ---------------------------------------------
	
	public void conectaMonitor() {
		try {
			socketMonitor = new Socket(ipMonitor, puertoMonitor);

			this.conectionMonitor = new ConectionMonitor(socketMonitor, this); // escucha al MONITOR

			DataInputStream dis = new DataInputStream(socketMonitor.getInputStream());

			this.puertoLocal = Integer.valueOf(dis.readUTF());

			this.ipServerPrincipal = dis.readUTF();

			this.puertoPrincipal = Integer.valueOf(dis.readUTF());
			this.rol = dis.readUTF();
			System.out.println("Rol recibido: " + this.rol);

			System.out.println("Puerto local recibido: " + puertoLocal);
			System.out.println("Puerto Principal recibido: " + puertoPrincipal);
			
			if (this.rol.equals("SECUNDARIO")) {
				socketConPrincipal = new Socket(ipServerPrincipal, this.puertoPrincipal);
				server.getControlador().appendMensajes("Sincronizando server respaldo");
				sinc = new SincronizacionEscucha(this);
				sinc.start();
			} else {
				this.ss = new ServerSocket(puertoLocal);
				this.conectionMonitor.iniciaHeartBeat();
				this.start();
			}
			
			//this.conectionMonitor.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void conectarConPrincipal() throws NumberFormatException, IOException {

		DataInputStream dis = new DataInputStream(socketMonitor.getInputStream());

		this.ipServerPrincipal = dis.readUTF();
		this.puertoPrincipal = Integer.valueOf(dis.readUTF());
		socketConPrincipal = new Socket(ipServerPrincipal, this.puertoPrincipal);
		server.getControlador().appendMensajes("Conecta con server principal");
		// sinc = new SincronizacionEscucha(this); ???
	}

	public void run() {
		Socket socket = null;

		try {
			while (Server.isTerminar() == false) {
				socket = ss.accept();
				this.listaSocketsServers.add(socket);
				System.out.println("Se creo el socket con el principal");
				server.getControlador().appendMensajes("Sincronizando server respaldoo");

//				ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());		// como creamos todos los servidores al principio siempre estan vacios los chats
//				dos.writeObject(this.server.getChats());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void sincronizaServers() {
		new Thread(() -> {
			try {
				Thread.sleep(10000);
				while (true) {
					// this.sinc.seteaClientes();
					if (Server.isTerminar() == false)
						System.out.println(this.listaSocketsServers);
						for (int i = 0; i < this.listaSocketsServers.size(); i++) {
							ObjectOutputStream dos = new ObjectOutputStream(
									this.listaSocketsServers.get(i).getOutputStream());
							dos.writeObject(this.server.getChats());
						}
					Thread.sleep(10000);
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}

		}).start();

	}
	
	
	
	// ------------------------------HEARTBEART ---------------------------------------------
	
	
}