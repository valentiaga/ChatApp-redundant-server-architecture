package front;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controladores.ControladorVistaInicial;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaInicial extends JFrame implements IVistaInicial{

	private JPanel contentPane;
	private JTextField txtPuerto;
	private JTextField txtIp;
	private ActionListener actionListenr; 
	private JTextField txtPuerto_1;
	private JButton btnModoEscucha;
	public JButton btnConectar;
	private ControladorVistaInicial cont = null;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInicial frame = new VistaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VistaInicial() {
		setTitle("BIENVENIDO!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setVisible(true);
		
		

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		txtPuerto = new JTextField();
		txtPuerto.setForeground(new Color(120, 120, 120));
		txtPuerto.setText("puerto");
		txtPuerto.setToolTipText("puerto");
		txtPuerto.setBackground(new Color(255, 255, 255));
		txtPuerto.setColumns(10);
		
		txtIp = new JTextField();
		txtIp.setForeground(new Color(120, 120, 120));
		txtIp.setText("IP");
		txtIp.setColumns(10);
		
		btnConectar = new JButton("Conectar");
		
		btnConectar.setActionCommand("CONECTAR");
		btnConectar.setBackground(new Color(245, 255, 246));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(57)
							.addComponent(btnConectar))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(83)
					.addComponent(txtPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtIp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnConectar)
					.addGap(62))
		);
		panel.setLayout(gl_panel);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		txtPuerto_1 = new JTextField();
		txtPuerto_1.setForeground(new Color(148, 148, 148));
		txtPuerto_1.setText("puerto");
		txtPuerto_1.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(47, Short.MAX_VALUE)
					.addComponent(txtPuerto_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(99, Short.MAX_VALUE)
					.addComponent(txtPuerto_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		btnModoEscucha = new JButton("Activar modo escucha");
		btnModoEscucha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		}); 
		panel_3.add(btnModoEscucha);
		btnModoEscucha.setActionCommand("MODOESCUCHA");
		btnModoEscucha.setForeground(new Color(0, 0, 0));
		//JOptionPane.showMessageDialog(null, "Estableciendo conexión...");
		//JOptionPane.showMessageDialog(null, "El usuario con puerto 5678 e IP: 192.228.17.57 no se encuentra en modo escucha. Inténtelo más tarde");
		this.cont = new ControladorVistaInicial(this);
	}


	public String getPuerto() {
		return this.txtPuerto.getText();
	}

	public String getIP() {
		// TODO Auto-generated method stub
		return this.txtIp.getText();
	}

	public void addActionListener(ActionListener actionListener) {
		this.btnConectar.addActionListener(actionListener);
		this.btnModoEscucha.addActionListener(actionListener);
		this.actionListenr = actionListener;
	}


	public void mostrarVentana() {
		// TODO Auto-generated method stub
		
	}


	public void setVisibleBtn() {
		this.btnModoEscucha.setVisible(true);
		
	}
	
//	public void keyReleased(KeyEvent e) {
//        boolean condition = this.getPuerto()> 1000 && this.getIP().length()>0;
//        this.btnConectar.setEnabled(condition);
//    }
	
	public void mostrarVentana(boolean cond) {
		this.setVisible(cond);
		
	}


	public String getPuertoEscucha() {
		return this.txtPuerto_1.getText();
	}

	
	

}
