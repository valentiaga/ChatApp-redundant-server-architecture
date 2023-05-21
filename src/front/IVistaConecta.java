package front;

import java.awt.event.ActionListener;

public interface IVistaConecta {
    String getNickname();
    void addActionListener(ActionListener actionListener);
    void mostrarVentana(boolean cond);
}