package controladores;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import front.IVistaInicial;
import front.IVistaModoEscucha;
import front.VistaInicial;

public class ControladorModoEscucha implements ActionListener {

	private IVistaModoEscucha vistaModoEscucha = null;
    //private Conexion conexion= new Conexion();

    public ControladorModoEscucha(IVistaModoEscucha vista) {
        this.vistaModoEscucha = vista;
        this.vistaModoEscucha.addActionListener(this);
        
    } 

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
        if (comando.equalsIgnoreCase("VOLVER")) {
        	this.vistaModoEscucha.mostrarVentana(false);
        	IVistaInicial vistaInicial = new VistaInicial();
        	vistaInicial.mostrarVentana(true);
        }
        	
        
		
	}

}
