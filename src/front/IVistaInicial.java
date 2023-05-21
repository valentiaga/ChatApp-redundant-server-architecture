package front;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public interface IVistaInicial 
{
	String getPuerto();
    String getIP();
    void addActionListener(ActionListener actionListener);
    void mostrarVentana(boolean cond);
	String getUser();
	
}
