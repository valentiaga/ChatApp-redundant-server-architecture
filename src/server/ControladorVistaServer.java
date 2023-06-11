package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import front.IVistaInicial;

public class ControladorVistaServer implements ActionListener{
	
	private IVistaServer vistaServer;
	private Server server;
	
	public ControladorVistaServer(IVistaServer vista) {
		this.vistaServer = vista;
		this.vistaServer.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("CLOSE_SERVER")) {
			try {
				this.server.closeServer();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			this.vistaServer.getTextArea().append("Este servidor fue desconectado");
			//aca podriamos cerrarla
		}
		
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	public void setListaConectados() {
		this.vistaServer.getTextArea().setText(this.server.getLista().toString());  
	}
	
	public void appendListaConectados (String dataCliente) {
		this.vistaServer.getTextArea().append("\n" + dataCliente);
	}
	
}
