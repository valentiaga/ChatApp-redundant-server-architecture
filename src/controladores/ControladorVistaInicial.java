package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import front.IVistaChat;
import front.IVistaInicial;
import front.IVistaModoEscucha;
import front.VistaChat;
import front.VistaEspera;
import back.Cliente;
import back.IEmisor;
import back.IReceptor;

public class ControladorVistaInicial implements ActionListener{
	
	private IVistaInicial vistaInicial = null;
    private Cliente cliente= null;
    
    public ControladorVistaInicial(IVistaInicial vista) {
        this.vistaInicial = vista;
        this.vistaInicial.addActionListener(this);
        this.cliente = new Cliente();
    } 

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
        if (comando.equalsIgnoreCase("CONECTAR")) {
        	boolean condition = !this.vistaInicial.getPuerto().equals("puerto")&& this.vistaInicial.getIP().length()>5;
   
        	
            try {
            	
            	if (condition == false)
            		JOptionPane.showMessageDialog(null, "El puerto o el IP son invalidos");
            	else {
            		System.out.println("Conexion exitosa\n");
            		
            		IVistaChat vistaChat = new VistaChat();
            		cliente.setVista(vistaChat);
            		
            		this.cliente.conectarServer(this.vistaInicial.getIP(), Integer.parseInt(this.vistaInicial.getPuerto()));
            		
//            		IVistaChat vistaChat = new vistaChat();
//            		conexion.setVista(vistaChat);
            		this.vistaInicial.mostrarVentana(false);  
            		
            		//vistaChat.setConexion(conexion);
            		vistaChat.getCont().setConexion(cliente);
            		vistaChat.mostrarVentana(true);
            		cliente.recibirMensajes();	       // crea un thread para poder recibir mensajes por el socket     	
            	}
            		
			} catch (NumberFormatException e1) {
				System.out.println("e1");
			} catch (UnknownHostException e1) {
				
			} catch (IOException e1) {
				//JOptionPane.showMessageDialog(null, "Lo siento. El receptor no se encuentra en modo escucha.");
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
        }
        else if (comando.equalsIgnoreCase("MODOESCUCHA")) {
        	if (!this.vistaInicial.getPuertoEscucha().equals("puerto")) {
        		IVistaModoEscucha vistaEscucha = new VistaEspera();
        		this.vistaInicial.mostrarVentana(false);
        		
        		vistaEscucha.mostrarVentana(true);  
        		try {
        			IVistaChat vistaChat = new VistaChat();
            		cliente.setVista(vistaChat);
					//this.cliente.conectarServer(Integer.parseInt(this.vistaInicial.getPuertoEscucha()), 0);
					vistaChat.getCont().setConexion(cliente);
//					VENTANA EMERGENTE PARA QUE EL USUARIO CONFIRME SI QUIERE INICIAR UN CHAT
//					if (this.conexion.getsocket().isConnected()) {
//						JOptionPane.showMessageDialog(null, "Un usuario quiere iniciar un chat.\nDesea aceptarlo?");
//					}
						
					//vistaChat.setConexion(conexion);
					//if(this.conexion.getsocket().isConnected() == true) {
					vistaEscucha.mostrarVentana(false);
					vistaChat.mostrarVentana(true);
					//}
					
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        	else
        		JOptionPane.showMessageDialog(null, "El puerto es invalido");
        }
        
		
	}
    
}
