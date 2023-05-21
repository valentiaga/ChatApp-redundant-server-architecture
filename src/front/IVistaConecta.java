package front;

import java.awt.event.ActionListener;

import back.Cliente;

public interface IVistaConecta {
    String getNicknameReceptor();
    void addActionListener(ActionListener actionListener);
    void mostrarVentana(boolean cond);
    public Cliente getCliente();
	public void setCliente(Cliente cliente);
}