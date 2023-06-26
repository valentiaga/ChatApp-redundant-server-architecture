package monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import server.IVistaServer;
import server.Server;

public class ControladorMonitor implements ActionListener{
	
	private IniciarMonitor iniciaMonitor;
	private Server server;
	
	public ControladorMonitor( IniciarMonitor iniciaMonitor) {
		this.iniciaMonitor = iniciaMonitor;
		this.iniciaMonitor.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public IniciarMonitor getIniciaMonitor() {
		return iniciaMonitor;
	}
	
	
}
