package monitor;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;

public class IniciarMonitor extends JFrame {

	private JPanel contentPane;
	private ControladorMonitor cont;
	private ActionListener actionListener;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarMonitor frame = new IniciarMonitor();
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
	public IniciarMonitor() {
		setTitle("MONITOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		 textArea = new JTextArea();
		 textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
		this.cont = new ControladorMonitor(this);
		Monitor monitor = Monitor.getInstance();
		monitor.setCont(cont);
		monitor.start();
	}

	public void addActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		
	}

	public ControladorMonitor getCont() {
		return cont;
	}

	public void setCont(ControladorMonitor cont) {
		this.cont = cont;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
