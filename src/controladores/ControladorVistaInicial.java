package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import back.Cliente;
<<<<<<< HEAD
import exception.IniciarException;
import front.IVistaConecta;
import front.IVistaInicial;
import front.vistaChat;
import front.vistaConecta;

public class ControladorVistaInicial implements ActionListener {
=======
import front.IVistaChat;
import front.IVistaInicial;
import front.VistaChat;
>>>>>>> main

	private IVistaInicial vistaInicial = null;
	private IVistaConecta vistaConecta = null;
	private Cliente cliente = null;
	private boolean registrado = true;

	public ControladorVistaInicial(IVistaInicial vista) {
		this.vistaInicial = vista;
		this.vistaInicial.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
<<<<<<< HEAD

		if (comando.equalsIgnoreCase("INICIAR")) {
			boolean condition = !this.vistaInicial.getPuerto().equals("puerto")
					&& this.vistaInicial.getIP().length() > 5 && !this.vistaInicial.getUser().equals("user");

			try {

				if (condition == false)
					JOptionPane.showMessageDialog(null, "Algún campo es inválido");
				else {

					this.cliente = new Cliente(this.vistaInicial.getUser(),
							Integer.parseInt(this.vistaInicial.getPuerto()), this.vistaInicial.getIP());

					this.cliente.creaConectionHandler();
					this.cliente.setContInicial(this);

					this.cliente.conectarServer();

				}

=======
		
        if (comando.equalsIgnoreCase("CONECTAR")) {
        	boolean condition = !this.vistaInicial.getPuerto().equals("puerto")&& this.vistaInicial.getIP().length()>5;
   
        	
            try {
            	
            	if (condition == false)
            		JOptionPane.showMessageDialog(null, "El puerto o el IP son invalidos");
            	else {
            		System.out.println("Conexion exitosa\n");
            		
            		IVistaChat vistaChat = new VistaChat();
            		conexion.setVista(vistaChat);
            		
            		this.conexion.conectarServer(this.vistaInicial.getIP(), Integer.parseInt(this.vistaInicial.getPuerto()));
            		
//            		IVistaChat vistaChat = new vistaChat();
//            		conexion.setVista(vistaChat);
            		this.vistaInicial.mostrarVentana(false);  
            		
            		//vistaChat.setConexion(conexion);
            		vistaChat.getCont().setConexion(conexion);
            		vistaChat.mostrarVentana(true);
            		conexion.recibirMensajes();	       // crea un thread para poder recibir mensajes por el socket     	
            	}
            		
>>>>>>> main
			} catch (NumberFormatException e1) {
				System.out.println("e1");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
<<<<<<< HEAD
		}

=======
        }
//        else if (comando.equalsIgnoreCase("MODOESCUCHA")) {
//        	if (!this.vistaInicial.getPuertoEscucha().equals("puerto")) {
//        		IVistaModoEscucha vistaEscucha = new vistaEspera();
//        		this.vistaInicial.mostrarVentana(false);
//        		
//        		vistaEscucha.mostrarVentana(true);  
//        		try {
//        			IVistaChat vistaChat = new vistaChat();
//            		conexion.setVista(vistaChat);
//					this.conexion.Conectar(Integer.parseInt(this.vistaInicial.getPuertoEscucha()));
//					vistaChat.getCont().setConexion(conexion);
////					VENTANA EMERGENTE PARA QUE EL USUARIO CONFIRME SI QUIERE INICIAR UN CHAT
////					if (this.conexion.getsocket().isConnected()) {
////						JOptionPane.showMessageDialog(null, "Un usuario quiere iniciar un chat.\nDesea aceptarlo?");
////					}
//						
//					//vistaChat.setConexion(conexion);
//					//if(this.conexion.getsocket().isConnected() == true) {
//					vistaEscucha.mostrarVentana(false);
//					vistaChat.mostrarVentana(true);
//					//}
//					
//					
//				} catch (NumberFormatException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//        		
//        	}
//        	else
//        		JOptionPane.showMessageDialog(null, "El puerto es invalido");
//        }
        
		
>>>>>>> main
	}

	public void ventanaEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public boolean isRegistrado() {
		return registrado;
	}

	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}

	public void vistaSiguiente() {
		vistaConecta = new vistaConecta(this.cliente.getNickname());
		ControladorVistaConecta cont = new ControladorVistaConecta(vistaConecta);
		cont.setVistaConecta(vistaConecta);
		cont.setCliente(this.cliente);

		this.cliente.setContConecta(cont);
		vistaConecta.setCont(cont);

		this.vistaInicial.mostrarVentana(false);
		vistaConecta.mostrarVentana(true);
	}
}