package server;

import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface IVistaServer {
	
	void addActionListener(ActionListener actionListener);
	void mostrarVentana(boolean cond);
	JTextArea getTextArea();
	JTextArea getTextAreaMensajes() ;
	JTextField getTextFieldPuerto();
	
	
}
