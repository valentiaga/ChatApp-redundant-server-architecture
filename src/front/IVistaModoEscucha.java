package front;

import java.awt.event.ActionListener;

public interface IVistaModoEscucha 
{
	void Temporizador();
	void addActionListener(ActionListener actionListener);
	void mostrarVentana(boolean cond);
	
}