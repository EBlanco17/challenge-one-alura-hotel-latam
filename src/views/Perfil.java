package views;

import model.InfoSession;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.MouseMotionAdapter;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class Perfil extends JFrame {

    private JPanel contentPane;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel labelRegistro;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Perfil frame = new Perfil();
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
    public Perfil() {
        super("Perfil");

        if (InfoSession.getUsuarioLogueado() == null) {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        }

        setIconImage(Toolkit.getDefaultToolkit().getImage(Perfil.class.getResource("/imagenes/perfil-del-usuario.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 944, 609);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

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

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(12, 138, 199));
        panelMenu.setBounds(0, 0, 257, 609);
        contentPane.add(panelMenu);
        panelMenu.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(50, 58, 150, 150);
        panelMenu.add(lblNewLabel_2);
        lblNewLabel_2.setIcon(new ImageIcon(Perfil.class.getResource("/imagenes/aH-150px.png")));

        JPanel btnRegistro = new JPanel();
        btnRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRegistro.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRegistro.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                RegistroReserva reservas = new RegistroReserva();
                reservas.setVisible(true);
                dispose();
            }
        });
        btnRegistro.setBounds(0, 312, 257, 56);
        btnRegistro.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnRegistro);
        btnRegistro.setLayout(null);

        labelRegistro = new JLabel("Registro de reservas");
        labelRegistro.setIcon(new ImageIcon(Perfil.class.getResource("/imagenes/reservado.png")));
        labelRegistro.setForeground(SystemColor.text);
        labelRegistro.setBounds(25, 11, 205, 34);
        labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
        btnRegistro.add(labelRegistro);

        JPanel btnBusqueda = new JPanel();
        btnBusqueda.setBounds(0, 369, 257, 56);
        btnBusqueda.setBackground(new Color(12, 138, 199));
        btnBusqueda.setLayout(null);

        btnBusqueda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBusqueda.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnBusqueda.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Busqueda busqueda = new Busqueda();
                busqueda.setVisible(true);
                dispose();
            }
        });

        panelMenu.add(btnBusqueda);

        JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
        lblBusquedaDeReservas.setIcon(new ImageIcon(Perfil.class.getResource("/imagenes/pessoas.png")));
        lblBusquedaDeReservas.setBounds(27, 11, 200, 34);
        lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
        lblBusquedaDeReservas.setForeground(Color.WHITE);
        lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnBusqueda.add(lblBusquedaDeReservas);


        JPanel btnPerfil = new JPanel();
        btnPerfil.setBounds(0, 255, 257, 56);
        btnPerfil.setBackground(new Color(12, 138, 199));
        btnPerfil.setLayout(null);

        btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPerfil.setBackground(new Color(118, 187, 223));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnPerfil.setBackground(new Color(12, 138, 199));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario home = new MenuUsuario();
                home.setVisible(true);
                dispose();
            }
        });

        panelMenu.add(btnPerfil);
        JLabel lblPerfil = new JLabel("Inicio");
        lblPerfil.setIcon(new ImageIcon(Perfil.class.getResource("/imagenes/home.png")));
        lblPerfil.setBounds(27, 11, 200, 34);
        lblPerfil.setHorizontalAlignment(SwingConstants.LEFT);
        lblPerfil.setForeground(Color.WHITE);
        lblPerfil.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnPerfil.add(lblPerfil);


        JSeparator separator = new JSeparator();
        separator.setBounds(26, 219, 201, 2);
        panelMenu.add(separator);
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 944, 36);
        contentPane.add(header);

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
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });

        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(891, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(new Color(118, 187, 223));
        panelNombre.setBounds(256, 84, 688, 121);
        contentPane.add(panelNombre);
        panelNombre.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Datos del usuario");
        lblNewLabel_1.setBounds(250, 11, 356, 42);
        panelNombre.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 24));

        JLabel labelNombre = new JLabel("");
        labelNombre.setBounds(35, 64, 500, 36);
        panelNombre.add(labelNombre);
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setFont(new Font("Roboto", Font.PLAIN, 33));
        labelNombre.setText(InfoSession.getUsuarioLogueado().getNombre() + " " + InfoSession.getUsuarioLogueado().getApellido());

        JLabel lblNewLabel = new JLabel("Bienvenido,");
        lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        lblNewLabel.setBounds(302, 234, 500, 46);
        contentPane.add(lblNewLabel);


        JLabel labelUser = new JLabel("");
        labelUser.setBounds(350, 270, 598, 66);
        labelUser.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelUser.setText("<html><strong> Nombre Usuario: </strong>" + InfoSession.getUsuarioLogueado().getUsuario() + "</html>");
        contentPane.add(labelUser);

        JLabel labelEmail = new JLabel("");
        labelEmail.setBounds(350, 300, 569, 88);
        labelEmail.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelEmail.setText("<html><strong> Email: </strong> " + InfoSession.getUsuarioLogueado().getEmail() + "</html>");
        contentPane.add(labelEmail);

        JLabel labelTelefono = new JLabel("");
        labelTelefono.setBounds(350, 340, 569, 88);
        labelTelefono.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelTelefono.setText("<html><strong> Telefono: </strong> " + InfoSession.getUsuarioLogueado().getTelefono() + "</html>");
        contentPane.add(labelTelefono);

        JPanel btnCerrar = new JPanel();
        JLabel labelCerrar = new JLabel("Cerrar sesión");
        labelCerrar.setIcon(new ImageIcon(Perfil.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
        labelCerrar.setHorizontalAlignment(SwingConstants.CENTER);
        labelCerrar.setForeground(Color.white);
        labelCerrar.setBounds(0, 0, 257, 56);
        labelCerrar.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnCerrar.setLayout(null);

        btnCerrar.setBackground(Color.red);
        btnCerrar.setBounds(450, 470, 257, 56);


        btnCerrar.addMouseListener(new MouseAdapter() {
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
                btnCerrar.setBackground(new Color(110, 6, 15));
                labelCerrar.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCerrar.setBackground(Color.red);
                labelCerrar.setForeground(Color.white);
            }
        });

        contentPane.add(btnCerrar);
        btnCerrar.add(labelCerrar);





    }

    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}

