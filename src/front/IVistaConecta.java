package front;

import java.awt.event.ActionListener;

<<<<<<< HEAD
import back.Cliente;
import controladores.ControladorVistaConecta;

public interface IVistaConecta {
    String getNicknameReceptor();
    void addActionListener(ActionListener actionListener);
    void mostrarVentana(boolean cond);
    public Cliente getCliente();
	public void setCliente(Cliente cliente);
	public void setCont(ControladorVistaConecta cont);
}
=======
public interface IVistaConecta {
	String getNickname();
	void addActionListener(ActionListener actionListener);
	void mostrarVentana(boolean cond);
}
>>>>>>> main
