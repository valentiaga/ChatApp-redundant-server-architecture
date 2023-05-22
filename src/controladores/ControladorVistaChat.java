package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.Cliente;
import front.IVistaChat;
import front.IVistaConecta;
import front.IVistaInicial;
import front.VistaInicial;
import front.vistaConecta;

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

        	//this.cliente.getMessageManager().enviaMensaje("El otro usuario se desconecto.\n");
        	this.cliente.getMessageManager().enviaComando("FINALIZARCHAT");
        	//this.cliente.getConectionHandler().terminarRecibirMensajes();
        	
        	this.abandonarChat();
//        	this.vistaChat.mostrarVentana(false);
//        	IVistaConecta vistaConecta =new vistaConecta(this.cliente.getNickname());
//        	ControladorVistaConecta cont = new ControladorVistaConecta(vistaConecta);
//			cont.setVistaConecta(vistaConecta);
//			cont.setCliente(this.cliente);
//			
//			this.cliente.setContConecta(cont);
//			vistaConecta.setCont(cont);
//        	vistaConecta.mostrarVentana(true);
        }
        else {
        	if (comando.equalsIgnoreCase("ENVIAR")) {
        		
        		this.cliente.getMessageManager().enviaMensaje(this.cliente.getNickname()+": "+this.vistaChat.getTxtIngreseTextoAqui().getText());
        		this.vistaChat.getTextArea().setText(this.vistaChat.getTextArea().getText()+"\n\t\t\t"+this.cliente.getNickname()+": "+  this.vistaChat.getTxtIngreseTextoAqui().getText()+"\n");
        		
        		this.vistaChat.getTxtIngreseTextoAqui().setText("");
        	 
        	}
        }

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setConexion(Cliente conexion) {
		this.cliente = conexion;
	}

	public void setVistaChat(IVistaChat vistaChat) {
		this.vistaChat=vistaChat;
		
	}

	public void appendTextArea(String text) {
		this.vistaChat.getTextArea().setText(this.vistaChat.getTextArea().getText()+"\n"+text);
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente=cliente;
		
	}
	
	public void abandonarChat() {
		this.vistaChat.mostrarVentana(false);
    	IVistaConecta vistaConecta =new vistaConecta(this.cliente.getNickname());
    	ControladorVistaConecta cont = new ControladorVistaConecta(vistaConecta);
		cont.setVistaConecta(vistaConecta);
		cont.setCliente(this.cliente);
		
		this.cliente.setContConecta(cont);
		vistaConecta.setCont(cont);
    	vistaConecta.mostrarVentana(true);
	}
	
}
