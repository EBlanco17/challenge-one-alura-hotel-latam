package DAO;

import model.Huesped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {
    final private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    public int insertarHuesped(Huesped huesped) {
        try (con) {
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) " +
                            "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                return ejecutarRegistro(huesped, statement);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int ejecutarRegistro(Huesped huesped, PreparedStatement statement) {
        try {
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());
            statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento().toString()));
            statement.setString(4, huesped.getNacionalidad());
            statement.setString(5, huesped.getTelefono());
            statement.setInt(6, huesped.getIdReserva());
            statement.executeUpdate();
            final ResultSet resultSet = statement.getGeneratedKeys();

            try (resultSet) {
                while (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();
        try {

            final PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM huespedes");

            try (statement) {
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try (resultSet) {
                    while (resultSet.next()) {
                        Huesped fila = new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("id_reserva"));
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Huesped huesped) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?");
            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento().toString()));
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReserva());
                statement.setInt(7, huesped.getId());
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM huespedes WHERE id = ?");
            try (statement) {
                statement.setInt(1, id);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarReserva(Integer idReserva) {

        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET id_reserva = null WHERE id_reserva = ?");
            try (statement) {

                statement.setInt(1, idReserva);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


