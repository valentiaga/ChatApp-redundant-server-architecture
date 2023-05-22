package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import front.IVistaChat;
import front.IVistaInicial;
import front.VistaInicial;
import back.Cliente;
import back.IEmisor;
import back.IReceptor;

public class ControladorVistaChat implements ActionListener {

	private IVistaChat vistaChat = null;
    private Cliente cliente= null;
    //private Conexion conexionReceptor = null;
    
    public ControladorVistaChat(IVistaChat vista) {
        this.vistaChat = vista;
        this.vistaChat.addActionListener(this);
       
    } 

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
        if (comando.equalsIgnoreCase("ABANDONAR")) {
        	
        	//this.vistaChat.getTextArea().setText(this.vistaChat.getTextArea().getText()+"\n"+"El otro usuario se desconecto.\n");
        	this.cliente.getMessageManager().enviaMensaje("El otro usuario se desconecto.\n");
        	this.cliente.getConectionHandler().terminarRecibirMensajes();
        	this.vistaChat.mostrarVentana(false);
        	IVistaInicial vistaInicial = new VistaInicial();
        	vistaInicial.mostrarVentana(true);
        }
        else {
        	if (comando.equalsIgnoreCase("ENVIAR")) {
        		this.cliente.getMessageManager().enviaMensaje(this.vistaChat.getTxtIngreseTextoAqui().getText());
        		this.vistaChat.getTextArea().setText(this.vistaChat.getTextArea().getText()+"\n\t\t\t\t" +this.vistaChat.getTxtIngreseTextoAqui().getText()+"\n");
        		
        		this.vistaChat.getTxtIngreseTextoAqui().setText("");
        	
        	}
        }

	}

	public Cliente getConexion() {
		return cliente;
	}

	public void setConexion(Cliente conexion) {
		this.cliente = conexion;
	}

	public void setVistaChat(IVistaChat vistaChat) {
		this.vistaChat=vistaChat;
		
	}

	public void setCliente(Cliente cliente) {
		this.cliente=cliente;
		
	}
	
}
