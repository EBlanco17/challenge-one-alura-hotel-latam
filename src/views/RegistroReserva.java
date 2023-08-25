package views;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Color;

import com.toedter.calendar.JDateChooser;
import controller.ReservaController;
import model.InfoSession;
import model.Reserva;

import java.awt.Font;
import java.sql.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class RegistroReserva extends JFrame {

	public static Integer idReserva;
	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroReserva frame = new RegistroReserva();
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
	public RegistroReserva() {
		super("Reservar");

		if (InfoSession.getUsuarioLogueado() == null) {
			Login login = new Login();
			login.setVisible(true);
			dispose();
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroReserva.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);



		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		// Código que crea los elementos de la interfáz gráfica

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		panel.add(lblCheckOut);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 17));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(RegistroReserva.class.getResource("/imagenes/Ha-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(RegistroReserva.class.getResource("/imagenes/reservas-img-3.png")));

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		panel.add(lblValor);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión en la aplicación?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					InfoSession.setUsuarioLogueado(null);
					Principal login = new Principal();
					login.setVisible(true);
					dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

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
		header.setBackground(Color.WHITE);
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);

		JLabel lblCalcular = new JLabel("Calcular Precio");
		lblCalcular.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalcular.setForeground(Color.WHITE);
		lblCalcular.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblCalcular.setBounds(0, 0, 122, 35);

		//Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(RegistroReserva.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, 1); // Agregar 1 días a la fecha actual
		txtFechaEntrada.setCalendar(date);
		panel.add(txtFechaEntrada);


		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(RegistroReserva.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 246, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		Calendar newDate = txtFechaEntrada.getCalendar();
		newDate.add(Calendar.DAY_OF_MONTH, 1); // Agregar 1 días a la fecha actual
		txtFechaSalida.setCalendar(newDate);
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));

		panel.add(txtFechaSalida);
		txtFechaEntrada.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				Calendar newDate = txtFechaEntrada.getCalendar();
				newDate.add(Calendar.DAY_OF_MONTH, 1); // Agregar 1 días a la fecha actual
				txtFechaSalida.setCalendar(newDate);
				txtValor.setText(String.valueOf(calcularValor(txtFechaEntrada, txtFechaSalida)));
			}
		});

		txtFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValor.setText(String.valueOf(calcularValor(txtFechaEntrada, txtFechaSalida)));
			}
		});

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 250, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtValor.setText(String.valueOf(calcularValor(txtFechaEntrada, txtFechaSalida)));
		panel.add(txtValor);

		txtValor.setColumns(40);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));

		panel.add(txtFormaPago);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Double valorReserva = calcularValor(txtFechaEntrada, txtFechaSalida);
				if (txtFechaEntrada.getDate() != null && txtFechaSalida.getDate() != null && valorReserva > 0.0) {
					Date fechaEntrada = formatearFecha(txtFechaEntrada);
					Date fechaSalida = formatearFecha(txtFechaSalida);

					valorReserva = calcularValor(txtFechaEntrada, txtFechaSalida);

					Reserva reserva = new Reserva(fechaEntrada, fechaSalida, valorReserva, txtFormaPago.getSelectedItem().toString());
					ReservaController controller = new ReservaController();
					idReserva = controller.insertarReserva(reserva);
					if (idReserva > 0){
						RegistroHuesped registro = new RegistroHuesped();
						registro.setVisible(true);
						dispose();
					}else{
						Error error = new Error("Error al registrar reserva");
						error.setVisible(true);

					}

				} else {
					Error error = new Error("Verfique los campos.");
					error.setVisible(true);

				}
			}
		});
		btnsiguiente.add(lblSiguiente);
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


	}

	public static Integer getIdReserva() {
		return idReserva;
	}

	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}
	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private Date formatearFecha(JDateChooser fecha){
		Date fechaFormateada = new Date(fecha.getDate().getTime());
		return Date.valueOf(fechaFormateada.toLocalDate());
	}
	private Double calcularValor(JDateChooser txtFechaEntrada, JDateChooser txtFechaSalida){
		Date fechaEntrada = formatearFecha(txtFechaEntrada);
		Date fechaSalida = formatearFecha(txtFechaSalida);

		Date fechaActual = new Date(System.currentTimeMillis());
		int dias = 0;

		//verificar fechas con la actual
		if (fechaEntrada.before(fechaActual) || fechaSalida.before(fechaActual)) {
			Error error = new Error("Verifique las fechas");
			error.setVisible(true);

		}else {

			//Cálculo del valor de la reserva
			dias = (int) ChronoUnit.DAYS.between(fechaEntrada.toLocalDate(), fechaSalida.toLocalDate());
			if (dias == 0){
				dias = 1;
			} else if (dias < 0) {
				dias = 0;
				Error error = new Error("verifque fecha salida");
				error.setVisible(true);
			}
		}
		Double valor = (double) (dias * 80000);
		return valor;
	}

}
