package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JScrollPane;

public class Main {

	private JFrame frame;
	private JTextField cuadroBusquedaTexto;
	private JTextField cuadroBuscarNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SingletonDB db = SingletonDB.getInstancia();
		frame = new JFrame();
		frame.setBounds(100, 100, 935, 674);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cuadroBusquedaTexto = new JTextField();
		cuadroBusquedaTexto.setBounds(80, 97, 280, 30);
		frame.getContentPane().add(cuadroBusquedaTexto);
		cuadroBusquedaTexto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 241, 893, 373);
		frame.getContentPane().add(scrollPane);
		
		JTextArea areaResultados = new JTextArea();
		scrollPane.setViewportView(areaResultados);
		areaResultados.setMargin(new Insets(2, 10, 10, 10));
		areaResultados.setEditable(false);
		areaResultados.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		cuadroBuscarNumero = new JTextField();
		cuadroBuscarNumero.setColumns(10);
		cuadroBuscarNumero.setBounds(650, 97, 102, 30);
		frame.getContentPane().add(cuadroBuscarNumero);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				areaResultados.setText(db.buscarTexto(cuadroBusquedaTexto.getText()));
			}
		});
		btnBuscar.setBounds(372, 97, 97, 30);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(764, 97, 97, 30);
		frame.getContentPane().add(btnMostrar);
		
		
		
		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setBounds(26, 212, 56, 16);
		frame.getContentPane().add(lblCdigo);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(112, 212, 75, 16);
		frame.getContentPane().add(lblContenido);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(764, 212, 56, 16);
		frame.getContentPane().add(lblFecha);
		
		JLabel lblContenido_1 = new JLabel("Contenido");
		lblContenido_1.setBounds(12, 104, 116, 16);
		frame.getContentPane().add(lblContenido_1);
		
		JLabel lblFecha_1 = new JLabel("o Fecha");
		lblFecha_1.setBounds(12, 118, 56, 16);
		frame.getContentPane().add(lblFecha_1);
		
		JLabel lblBuscarPor = new JLabel("Buscar por");
		lblBuscarPor.setBounds(12, 89, 75, 16);
		frame.getContentPane().add(lblBuscarPor);
		
		JLabel label = new JLabel("Buscar por");
		label.setBounds(546, 96, 75, 16);
		frame.getContentPane().add(label);
		
		JLabel lblNumeorDeCinta = new JLabel("numero de cinta");
		lblNumeorDeCinta.setBounds(546, 111, 102, 16);
		frame.getContentPane().add(lblNumeorDeCinta);
		
		JLabel lblBuscadorVhsAuditorio = new JLabel("Buscador VHS Auditorio");
		lblBuscadorVhsAuditorio.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		lblBuscadorVhsAuditorio.setBounds(12, 13, 387, 59);
		frame.getContentPane().add(lblBuscadorVhsAuditorio);
	}
}
