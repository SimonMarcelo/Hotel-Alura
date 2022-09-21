package ar.com.hotelalura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		Panel panel = new Panel();
		panel.setBackground(Color.lightGray);
		panel.setBounds(0, 0, 910, 537);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(-50, 0, 732, 501);
		imagenFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/menu-img.png")));
		panel.add(imagenFondo);

		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/aH-150px.png")));
		panel.add(logo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 500, 910, 37);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCopyR = new JLabel("Desarrollado por Marcelo Simon © 2022");
		lblCopyR.setBounds(315, 11, 284, 19);
		lblCopyR.setForeground(new Color(240, 248, 255));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 15));
		panel_1.add(lblCopyR);

		// Barra para controlar la ventana
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.lightGray);
		panel.add(header);
//
//		// Botón salir
//		JPanel btnexit = new JPanel();
//		btnexit.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.exit(0);
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				btnexit.setBackground(Color.red);
//				labelExit.setForeground(Color.white);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				btnexit.setBackground(Color.white);
//				labelExit.setForeground(Color.black);
//			}
//		});
//		btnexit.setLayout(null);
//		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//		btnexit.setBackground(Color.lightGray);
//		btnexit.setBounds(857, 0, 53, 36);
//		header.add(btnexit);
//
//		labelExit = new JLabel("X");
//		labelExit.setBounds(0, 0, 53, 36);
//		btnexit.add(labelExit);
//		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
//		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		// Botón Login
		JPanel btnLogin = new JPanel();
		btnLogin.setBounds(754, 300, 83, 70);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(Color.lightGray);
		panel.add(btnLogin);

		JLabel imagenLogin = new JLabel("");
		imagenLogin.setBounds(0, 0, 80, 70);
		btnLogin.add(imagenLogin);
		imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/login.png")));

		JLabel lblTitulo = new JLabel("Login");
		lblTitulo.setBounds(754, 265, 83, 24);
		lblTitulo.setBackground(SystemColor.window);
		panel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(SystemColor.textHighlight);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

		
		// Botón Salir
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] opciones = { "Aceptar", "Cancelar" };
				int eleccion = JOptionPane.showOptionDialog(rootPane, "¿Está seguro que desea cerrar la aplicacion?",
						"Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						opciones, "Aceptar");
				if (eleccion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/cerrar-sesion.png")));
		btnExit.setLayout(null);
		btnExit.setBorder(null);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		btnExit.setForeground(SystemColor.textHighlight);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(852, 450, 44, 39);
		panel.add(btnExit);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
