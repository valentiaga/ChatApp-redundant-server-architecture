package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import back.Cliente;
import exception.IniciarException;
import front.IVistaConecta;
import front.IVistaInicial;
import front.vistaChat;
import front.vistaConecta;

public class ControladorVistaInicial implements ActionListener {

	private IVistaInicial vistaInicial = null;
	private Cliente cliente = null;

	public ControladorVistaInicial(IVistaInicial vista) {
		this.vistaInicial = vista;
		this.vistaInicial.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("INICIAR")) {
			boolean condition = !this.vistaInicial.getPuerto().equals("puerto")
					&& this.vistaInicial.getIP().length() > 5 && !this.vistaInicial.getUser().equals("user");

			try {

				if (condition == false)
					JOptionPane.showMessageDialog(null, "Algún campo es inválido");
				else {

					this.cliente = new Cliente(this.vistaInicial.getUser(),
							Integer.parseInt(this.vistaInicial.getPuerto()), this.vistaInicial.getIP());
					try {
						System.out.println("Antes de conectar");
						this.cliente.conectarServer();
						System.out.println("despues de conectar");
						IVistaConecta vistaConecta = new vistaConecta(this.cliente.getNickname());

						// creo controlador conecta
						ControladorVistaConecta cont = new ControladorVistaConecta(vistaConecta);
						cont.setVistaConecta(vistaConecta);
						cont.setCliente(this.cliente);

						this.cliente.setContConecta(cont);
						vistaConecta.setCont(cont);

						System.out.println(cliente);
						this.vistaInicial.mostrarVentana(false);

						vistaConecta.mostrarVentana(true);
					} catch (IniciarException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

			} catch (NumberFormatException e1) {
				System.out.println("e1");
			} catch (UnknownHostException e1) {

			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

	}

}