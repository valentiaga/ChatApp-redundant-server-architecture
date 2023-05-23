package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
<<<<<<< HEAD
=======
import java.net.UnknownHostException;
>>>>>>> main

import javax.swing.JOptionPane;

import back.Cliente;
<<<<<<< HEAD
import exception.CreaChatException;
import exception.UserNotAvailableException;
import front.IVistaChat;
import front.IVistaConecta;
=======
import front.IVistaChat;
import front.IVistaConecta;
import front.IVistaInicial;
>>>>>>> main
import front.vistaChat;

public class ControladorVistaConecta implements ActionListener {

	private IVistaConecta vistaConecta = null;
<<<<<<< HEAD
	private Cliente cliente = null;
=======
	private Cliente conexion = null;
>>>>>>> main

	public ControladorVistaConecta(IVistaConecta vista) {
		this.vistaConecta = vista;
		this.vistaConecta.addActionListener(this);
<<<<<<< HEAD
=======
		this.conexion = new Cliente();
>>>>>>> main
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("CONECTAR")) {
<<<<<<< HEAD
			boolean condition = !this.vistaConecta.getNicknameReceptor().equals("user");

			try {
				if (condition == false)
					JOptionPane.showMessageDialog(null, "Ingrese un usuario válido");
				else
					this.cliente.conectarReceptor(this.vistaConecta.getNicknameReceptor());

			} catch (UserNotAvailableException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (NumberFormatException e1) {
				System.out.println("e1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CreaChatException e1) {
				iniciaChat();
			}
=======
			boolean condition = !this.vistaConecta.getNickname().equals("user");
	

			try {

				if (condition == false)
					JOptionPane.showMessageDialog(null, "Ingrese un nickname");
				else {
////					System.out.println("Conexion exitosa\n");
//
//					IVistaChat vistaChat = new vistaChat();
//					conexion.setVista(vistaChat);
//
//					this.conexion.conectarServer(this.vistaConecta.getIP(),
//							Integer.parseInt(this.vistaConecta.getPuerto()));
//
//
//					this.vistaInicial.mostrarVentana(false);
//
//					vistaChat.getCont().setConexion(conexion);
//					vistaChat.mostrarVentana(true);
//					conexion.recibirMensajes(); // crea un thread para poder recibir mensajes por el socket
				}

			} catch (NumberFormatException e1) {
				System.out.println("e1");
			} 
//				catch (UnknownHostException e1) {
//
//			} catch (IOException e1) {
//				JOptionPane.showMessageDialog(null, e1.getMessage());
//			}
>>>>>>> main
		}

	}

<<<<<<< HEAD
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

	public void ventanaEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

}
=======
}
>>>>>>> main
