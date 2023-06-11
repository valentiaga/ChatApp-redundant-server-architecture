package server;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VistaServer extends JFrame implements IVistaServer{

	private JPanel contentPane;
	private JButton btnClose;
	private ActionListener actionListener;
	//ControladorVistaServer controlador;
	JTextArea textAreaLista;
	
	//private Server server;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaServer frame = new VistaServer();
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
	public VistaServer() {
		setTitle("SERVIDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setVisible(true);
		setContentPane(contentPane);
		
		textAreaLista = new JTextArea();
		textAreaLista.setEditable(false);
		
		btnClose = new JButton("Cerrar servidor");
		btnClose.setActionCommand("CLOSE_SERVER");
		
		JTextArea textAreaMensajes = new JTextArea();
		textAreaMensajes.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAreaLista, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textAreaMensajes, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(144)
							.addComponent(btnClose)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAreaMensajes))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(textAreaLista, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnClose)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		//controlador = new ControladorVistaServer(this);
	}

	
	public JTextArea getTextArea() {
		return textAreaLista;
	}
	
	
	@Override
	public void addActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.btnClose.addActionListener(actionListener);
	}

	@Override
	public void mostrarVentana(boolean cond) {
		// TODO Auto-generated method stub
		
	}
}
