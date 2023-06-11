package server;

import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public interface IVistaServer {
	
	void addActionListener(ActionListener actionListener);
	void mostrarVentana(boolean cond);
	JTextArea getTextArea();
	JTextArea getTextAreaMensajes() ;
	
	
}
