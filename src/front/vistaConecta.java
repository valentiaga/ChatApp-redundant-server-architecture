package front;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controladores.ControladorVistaConecta;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;


public class vistaConecta  extends JFrame implements IVistaConecta{
	
	private JPanel contentPane;
	private JTextField txtPuerto;
	private ActionListener actionListenr; 
	public JButton btnConectar;
	private ControladorVistaConecta cont = null;
	private JLabel lblNewLabel;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaConecta frame = new vistaConecta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vistaConecta() {
		setTitle("INICIE UNA CONVERSACION!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setVisible(true);
		
		

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("Ingrese un usuario para contactar o espere");
		
		btnConectar = new JButton("CONECTAR");
		
		btnConectar.setActionCommand("CONECTAR");
		btnConectar.setBackground(new Color(245, 255, 246));
		
		txtPuerto = new JTextField();
		txtPuerto.setForeground(new Color(169, 169, 169));
		txtPuerto.setText("user");
		txtPuerto.setToolTipText("puerto");
		txtPuerto.setBackground(new Color(255, 255, 255));
		txtPuerto.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(102)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(157)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnConectar)
								.addComponent(txtPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel)
					.addGap(71)
					.addComponent(txtPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(btnConectar)
					.addGap(39))
		);
		contentPane.setLayout(gl_contentPane);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//JOptionPane.showMessageDialog(null, "Estableciendo conexión...");
		//JOptionPane.showMessageDialog(null, "El usuario con puerto 5678 e IP: 192.228.17.57 no se encuentra en modo escucha. Inténtelo más tarde");
		this.cont = new ControladorVistaConecta(this);
	}
	
	



	@Override
	public void addActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarVentana(boolean cond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNickname() {
		// TODO Auto-generated method stub
		return null;
	}

}