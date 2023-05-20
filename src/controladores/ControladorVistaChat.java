package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.Cliente;
import front.IVistaChat;
import front.IVistaInicial;
import front.VistaInicial;

public class ControladorVistaChat implements ActionListener {

	private IVistaChat vistaChat = null;
    private Cliente conexion= null;
    //private Conexion conexionReceptor = null;
    
    public ControladorVistaChat(IVistaChat vista) {
        this.vistaChat = vista;
        this.vistaChat.addActionListener(this);
       
    } 

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
        if (comando.equalsIgnoreCase("ABANDONAR")) {
        	
        	//this.vistaChat.getTextArea().setText(this.vistaChat.getTextArea().getText()+"\n"+"El otro usuario se desconecto.\n");
        	this.conexion.getMessageManager().enviaMensaje("El otro usuario se desconecto.\n");
        	this.conexion.getConectionHandler().terminarRecibirMensajes();
        	this.vistaChat.mostrarVentana(false);
        	IVistaInicial vistaInicial = new VistaInicial();
        	vistaInicial.mostrarVentana(true);
        }
        else {
        	if (comando.equalsIgnoreCase("ENVIAR")) {
        		this.conexion.getMessageManager().enviaMensaje(this.vistaChat.getTxtIngreseTextoAqui().getText());
        		this.vistaChat.getTextArea().setText(this.vistaChat.getTextArea().getText()+"\n\t\t\t\t" +this.vistaChat.getTxtIngreseTextoAqui().getText()+"\n");
        		
        		this.vistaChat.getTxtIngreseTextoAqui().setText("");
        	
        	}
        }

	}

	public Cliente getConexion() {
		return conexion;
	}

	public void setConexion(Cliente conexion) {
		this.conexion = conexion;
	}
	
}
