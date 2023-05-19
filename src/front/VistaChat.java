package front;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import UI.IVistaChat;
import UI.vistaChat;
import back.Cliente;
import back.MessageManager;
import controladores.ControladorVistaChat;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

public class VistaChat extends JFrame implements IVistaChat{

	private JPanel contentPane;
	private JTextField txtIngreseTextoAqui;
	private ActionListener actionListener = null;
	private JButton btnAbandonar;
	private JButton btnEnviar;
	private JScrollPane jScrollPane1;
	private JTextArea textArea;
	//private Conexion conexion = null;
	private ControladorVistaChat cont;
	private boolean cerrarVentana = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaChat frame = new vistaChat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vistaChat() { 
		setTitle("CHAT EN TIEMPO REAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//this.setVisible(true);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Ya estan en contacto! ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		btnAbandonar = new JButton("Abandonar chat");
		btnAbandonar.setActionCommand("ABANDONAR");
		btnAbandonar.setBackground(new Color(95, 95, 95));
		btnAbandonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAbandonar.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		txtIngreseTextoAqui = new JTextField();
		txtIngreseTextoAqui.setText("Ingrese texto aquí");
		txtIngreseTextoAqui.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtIngreseTextoAqui.setForeground(new Color(127, 127, 127));
		txtIngreseTextoAqui.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setActionCommand("ENVIAR");
		
		jScrollPane1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(142)
							.addComponent(lblNewLabel)
							.addGap(29)
							.addComponent(btnAbandonar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtIngreseTextoAqui)
							.addGap(18)
							.addComponent(btnEnviar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAbandonar)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIngreseTextoAqui, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnviar)))
		);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		jScrollPane1.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
		//JOptionPane.showInternalMessageDialog(null, "Uno de los participantes ha abandonado el chat. Ha sido desconectado.");
		cont = new ControladorVistaChat(this);
	}


	public void addActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.btnAbandonar.addActionListener(actionListener);
		this.btnEnviar.addActionListener(actionListener);
		
	}

	public void mostrarVentana(boolean cond) {
		this.setVisible(cond);
		this.setEnabled(cond);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JScrollPane getjScrollPane1() {
		return jScrollPane1;
	}

	public JTextField getTxtIngreseTextoAqui() {
		return txtIngreseTextoAqui;
	}

	public void setText(String text) {
		// TODO Auto-generated method stub
	}

//	public void setConexion(Conexion conexion) {
//		this.conexion = conexion;
//		this.cont.setConexion(conexion);
//	}
//	
//	public Conexion getConexion() {
//		return this.conexion;
//	}

	public ControladorVistaChat getCont() {
		return cont;
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}
	
	
}

