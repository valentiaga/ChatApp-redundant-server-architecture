package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import back.Cliente;
import front.IVistaChat;
import front.IVistaConecta;
import front.IVistaInicial;
import front.vistaChat;

public class ControladorVistaConecta implements ActionListener {

	private IVistaConecta vistaConecta = null;
	private Cliente conexion = null;

	public ControladorVistaConecta(IVistaConecta vista) {
		this.vistaConecta = vista;
		this.vistaConecta.addActionListener(this);
		this.conexion = new Cliente();
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("CONECTAR")) {
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
		}

	}

}
