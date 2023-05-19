package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import front.IVistaChat;
import front.IVistaInicial;
import front.IVistaModoEscucha;
import front.vistaChat;
import front.vistaEspera;
import back.Cliente;
import back.IEmisor;
import back.IReceptor;

public class ControladorVistaInicial implements ActionListener{
	
	private IVistaInicial vistaInicial = null;
    private Cliente conexion= null;
    
    public ControladorVistaInicial(IVistaInicial vista) {
        this.vistaInicial = vista;
        this.vistaInicial.addActionListener(this);
        this.conexion = new Cliente();
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
            		
            		IVistaChat vistaChat = new vistaChat();
            		conexion.setVista(vistaChat);
            		
            		this.conexion.conectarServer(this.vistaInicial.getIP(), Integer.parseInt(this.vistaInicial.getPuerto()));
            		
//            		IVistaChat vistaChat = new vistaChat();
//            		conexion.setVista(vistaChat);
            		this.vistaInicial.mostrarVentana(false);  
            		
            		//vistaChat.setConexion(conexion);
            		vistaChat.getCont().setConexion(conexion);
            		vistaChat.mostrarVentana(true);
            		conexion.recibirMensajes();	       // crea un thread para poder recibir mensajes por el socket     	
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
        		IVistaModoEscucha vistaEscucha = new vistaEspera();
        		this.vistaInicial.mostrarVentana(false);
        		
        		vistaEscucha.mostrarVentana(true);  
        		try {
        			IVistaChat vistaChat = new vistaChat();
            		conexion.setVista(vistaChat);
					this.conexion.Conectar(Integer.parseInt(this.vistaInicial.getPuertoEscucha()));
					vistaChat.getCont().setConexion(conexion);
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
