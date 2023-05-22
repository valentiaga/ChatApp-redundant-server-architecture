package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import back.Cliente;
import exception.CreaChatException;
import exception.UserNotAvailableException;
import front.IVistaChat;
import front.IVistaConecta;
import front.vistaChat;

public class ControladorVistaConecta implements ActionListener {

	private IVistaConecta vistaConecta = null;
	private Cliente cliente = null;

	public ControladorVistaConecta(IVistaConecta vista) {
		this.vistaConecta = vista;
		this.vistaConecta.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("CONECTAR")) {
			boolean condition = !this.vistaConecta.getNicknameReceptor().equals("user");

			try {
				if (condition == false)
					JOptionPane.showMessageDialog(null, "Ingrese un usuario válido");
				else {
						
					//this.cliente=this.vistaConecta.getCliente();
					
					this.cliente.conectarReceptor(this.vistaConecta.getNicknameReceptor());
					 
////                   System.out.println("Conexion exitosa\n");
//
//                    IVistaChat vistaChat = new vistaChat();
//                    conexion.setVista(vistaChat);
//
//                    this.conexion.conectarServer(this.vistaConecta.getIP(),
//                            Integer.parseInt(this.vistaConecta.getPuerto()));
//
//
//                    this.vistaInicial.mostrarVentana(false);
//
//                    vistaChat.getCont().setConexion(conexion);
//                    vistaChat.mostrarVentana(true);
//                    conexion.recibirMensajes(); // crea un thread para poder recibir mensajes por el socket
				}  

				
			} catch (UserNotAvailableException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			catch (NumberFormatException e1) {
				System.out.println("e1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CreaChatException e1) {
				iniciaChat();
			} 

		}

	}
	
	public void iniciaChat() {
		IVistaChat vistaChat = new vistaChat();
		ControladorVistaChat cont = new ControladorVistaChat(vistaChat);
		cont.setVistaChat(vistaChat);
		cont.setCliente(this.cliente);
		
		this.cliente.setContChat(cont);
		vistaChat.setCont(cont);
		
		this.vistaConecta.mostrarVentana(false);
		vistaChat.mostrarVentana(true);
	}

	public void setVistaConecta(IVistaConecta vistaConecta) {
		this.vistaConecta = vistaConecta;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void noRegistrado(String user) {
		JOptionPane.showMessageDialog(null, user+" no se encuentra reistrado en el sistema.");
	}
	
	

}