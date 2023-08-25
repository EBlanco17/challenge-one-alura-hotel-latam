package DAO;

import model.Huesped;
import model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    final private Connection con;

    public ReservaDAO(Connection con){
        this.con = con;
    }

    public int insertarReserva(Reserva reserva) {
        try (con){
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) " +
                            "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try(statement){
                return ejecutarRegistro(reserva, statement);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private int ejecutarRegistro(Reserva reserva, PreparedStatement statement) {
        try{
            statement.setDate(1, reserva.getFechaEntrada());
            statement.setDate(2, reserva.getFechaSalida());
            statement.setDouble(3, reserva.getValor());
            statement.setString(4, reserva.getFormaPago());
            statement.executeUpdate();
            final ResultSet resultSet = statement.getGeneratedKeys();
            try(resultSet){
                while (resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Reserva> listar(){
        List<Reserva> resultado = new ArrayList<>();
        try{
            final PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM reservas");
            try(statement){
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    while (resultSet.next()){
                        Reserva fila = new Reserva(
                                resultSet.getInt("id"),
                                resultSet.getDate("fecha_entrada"),
                                resultSet.getDate("fecha_salida"),
                                resultSet.getDouble("valor"),
                                resultSet.getString("forma_pago")
                        );
                        resultado.add(fila);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int modificar(Reserva reserva) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?");
            try (statement) {
                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
                statement.setInt(5, reserva.getId());
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM reservas WHERE id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    public List<Reserva> buscar(Integer id) {

        List<Reserva> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT R.*, H.*" +
                    " FROM reservas R" +
                    " JOIN huespedes H ON R.id = H.id_reserva" +
                    " WHERE R.id= ?;");

            try (statement) {

                statement.setInt(1, id);
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try (resultSet) {
                    while (resultSet.next()) {
                        Integer reservaId = resultSet.getInt("R.id");
                        Date fechaEntrada = resultSet.getDate("R.fecha_entrada");
                        Date fechaSalida = resultSet.getDate("R.fecha_salida");
                        Double valor = resultSet.getDouble("R.valor");
                        String formaPago = resultSet.getString("R.forma_pago");

                        var reserva = resultado
                                .stream()
                                .filter(res -> res.getId().equals(reservaId))
                                .findAny()
                                .orElseGet(()->{
                                    Reserva res = new Reserva(reservaId, fechaEntrada, fechaSalida, valor, formaPago);
                                    resultado.add(res);
                                    return res;
                                });

                        Huesped huesped = new Huesped(
                                resultSet.getInt("H.id"),
                                resultSet.getString("H.nombre"),
                                resultSet.getString("H.apellido"),
                                resultSet.getDate("H.fecha_nacimiento"),
                                resultSet.getString("H.nacionalidad"),
                                resultSet.getString("H.telefono"),
                                resultSet.getInt("H.id_reserva"));
                        reserva.agregar(huesped);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public List<Reserva> buscar(String apellido) {

        List<Reserva> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT R.*, H.*" +
                    " FROM reservas R" +
                    " JOIN huespedes H ON R.id = H.id_reserva" +
                    " WHERE H.apellido LIKE ?;");

            try (statement) {

                statement.setString(1, "%"+apellido+"%");

                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try (resultSet) {
                    while (resultSet.next()) {
                        Integer reservaId = resultSet.getInt("R.id");
                        Date fechaEntrada = resultSet.getDate("R.fecha_entrada");
                        Date fechaSalida = resultSet.getDate("R.fecha_salida");
                        Double valor = resultSet.getDouble("R.valor");
                        String formaPago = resultSet.getString("R.forma_pago");


                        Reserva res = new Reserva(reservaId, fechaEntrada, fechaSalida, valor, formaPago);
                        resultado.add(res);

                        Huesped huesped = new Huesped(
                                resultSet.getInt("H.id"),
                                resultSet.getString("H.nombre"),
                                resultSet.getString("H.apellido"),
                                resultSet.getDate("H.fecha_nacimiento"),
                                resultSet.getString("H.nacionalidad"),
                                resultSet.getString("H.telefono"),
                                resultSet.getInt("H.id_reserva"));
                        res.agregar(huesped);
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
