package views;

import controller.HuespedController;
import controller.ReservaController;
import model.Huesped;
import model.InfoSession;
import model.Reserva;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private HuespedController huespedController;
	private ReservaController reservaController;
	/**
	 * Create the frame.
	 */
	public Busqueda() {
		if (InfoSession.getUsuarioLogueado() == null) {
			Login login = new Login();
			login.setVisible(true);
			dispose();
		}

		huespedController = new HuespedController();
		reservaController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);


		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 20));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);




		tbReservas = new JTable();
		modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Hacer no editable la columna "Número de Huesped" (columna 0)
				return column != 0;
			}
		};

		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		tbReservas = new JTable(modelo);
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));

		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);


		tbHuespedes = new JTable();
		modeloHuesped = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Hacer no editable la columna "Número de Huesped" (columna 0)
				return column != 0;
			}
		};

		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");

		tbHuespedes = new JTable(modeloHuesped);
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));


		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		cargarTablaReservas();
		cargarTablaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
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
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

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
				btnAtras.setBackground(new Color(171, 169, 169));
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(171, 169, 169));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("");
		labelAtras.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/home.png")));
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

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
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(201, 201, 201));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblBuscar = new JLabel("");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa-1.png")));
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnbuscar.setBackground(new Color(22, 97, 150));
				lblBuscar.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnbuscar.setBackground(new Color(201, 201, 201));
				lblBuscar.setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				reservaController = new ReservaController();
				List<Reserva> lista;
				try {
					int id = Integer.parseInt(txtBuscar.getText());
					lista = reservaController.buscar(id);
				} catch (NumberFormatException ex) {
					lista = reservaController.buscar(txtBuscar.getText());
				}
				if (lista.isEmpty()) {
					Error error = new Error("No se encontraron resultados");
					error.setVisible(true);
					return;
				}
				limpiarTabla(modelo);
				limpiarTabla(modeloHuesped);
				lista.forEach(reserva -> {
					modelo.addRow(new Object[]{
							reserva.getId(),
							reserva.getFechaEntrada(),
							reserva.getFechaSalida(),
							reserva.getValor(),
							reserva.getFormaPago()
					});
					var huespedes = reserva.getHuespedes();
					huespedes.forEach(huesped -> {
						modeloHuesped.addRow(new Object[]{
								huesped.getId(),
								huesped.getNombre(),
								huesped.getApellido(),
								huesped.getFechaNacimiento(),
								huesped.getNacionalidad(),
								huesped.getTelefono(),
								huesped.getIdReserva()
						});
					});
				});
			}
		});

		contentPane.add(btnbuscar);

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(201, 201, 201));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblEditar = new JLabel("");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		lblEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(87, 194, 60));
				lblEditar.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(new Color(201, 201, 201));
				lblEditar.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.isShowing()) {
					modificarReserva();
					limpiarTabla(modelo);
					cargarTablaReservas();
				} else {
					modificarHuesped();
					limpiarTabla(modeloHuesped);
					cargarTablaHuespedes();
				}
			}
		});

		contentPane.add(btnEditar);


		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(201, 201, 201));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblEliminar = new JLabel("");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		lblEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.add(lblEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(201, 52, 52, 255));
				lblEliminar.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(new Color(201, 201, 201));
				lblEliminar.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.isShowing()) {
					eliminarReserva();
					limpiarTabla(modelo);
					cargarTablaReservas();
				} else {
					eliminarHuesped();
					limpiarTabla(modeloHuesped);
					cargarTablaHuespedes();
				}
			}
		});

		contentPane.add(btnEliminar);
		setResizable(false);

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

	private void limpiarTabla(DefaultTableModel modelo) {
		modelo.getDataVector().clear();
	}

	private boolean tieneFilaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	}

	private void cargarTablaReservas() {
		var reservas = this.reservaController.listar();
		for (Reserva reserva: reservas) {
			modelo.addRow(new Object[] {
					reserva.getId(),
					reserva.getFechaEntrada(),
					reserva.getFechaSalida(),
					reserva.getValor(),
					reserva.getFormaPago()
			});
		}
	}

	private void modificarReserva(){
		if (tieneFilaElegida(tbReservas)) {
			Error error = new Error("Por favor, elije un item");
			error.setVisible(true);
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					try{
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						Date fechaEntrada = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
						Date fechaSalida = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
						Double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
						Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);

						this.reservaController.modificar(reserva);

						Exito exito = new Exito("Reserva modificada con éxito!");
						exito.setVisible(true);

					}catch(Exception e){
						Error error = new Error("Error en el proceso");
						error.setVisible(true);

					}

				},null);

	}

	private void eliminarReserva(){
		if (tieneFilaElegida(tbReservas)) {
			Error error = new Error("Por favor, elije un item");
			error.setVisible(true);
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

					int itemEliminados;

					itemEliminados = this.reservaController.eliminar(id);

					modelo.removeRow(tbReservas.getSelectedRow());
					if (itemEliminados > 0){
						Exito exito = new Exito(itemEliminados + " Item eliminado con éxito!");
						exito.setVisible(true);

					}else {
						Error error = new Error("Error en el proceso");
						error.setVisible(true);

					}

				}, null);
	}
	private void cargarTablaHuespedes() {
		var huespedes= this.huespedController.listar();
		for (Huesped huesped : huespedes) {
			modeloHuesped.addRow(new Object[] {
					huesped.getId(),
					huesped.getNombre(),
					huesped.getApellido(),
					huesped.getFechaNacimiento(),
					huesped.getNacionalidad(),
					huesped.getTelefono(),
					huesped.getIdReserva()
			});
		}
	}

	private void modificarHuesped(){
		if (tieneFilaElegida(tbHuespedes)) {
			Error error = new Error("Por favor, elije un item");
			error.setVisible(true);
			return;
		}

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					try {
						Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
						Date fechaNacimiento = Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
						String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
						String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
						Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

						Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
						int filasModificadas;

						filasModificadas = this.huespedController.modificar(huesped);
						Exito exito = new Exito("Huesped modificado con éxito!");
						exito.setVisible(true);

					}catch (Exception e) {
						Error error = new Error("Error en el proceso");
						error.setVisible(true);
					}

				}, null);
	}

	private void eliminarHuesped(){
		if (tieneFilaElegida(tbHuespedes)) {
			Error error = new Error("Por favor, elije un item");
			error.setVisible(true);
			return;
		}

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

					int itemEliminados;

					itemEliminados = this.huespedController.eliminar(id);

					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

					if (itemEliminados > 0){
						Exito exito = new Exito(itemEliminados + " Item eliminado con éxito!");
						exito.setVisible(true);

					}else {
						Error error = new Error("Error en el proceso");
						error.setVisible(true);
					}

				}, null);
	}

}
