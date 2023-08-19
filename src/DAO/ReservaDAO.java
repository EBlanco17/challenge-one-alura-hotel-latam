package DAO;

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
                    "INSERT INTO RESERVAS (Fecha_entrada, Fecha_salida, Valor, Forma_pago) " +
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
                    "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS");
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
                    "UPDATE RESERVAS SET Fecha_entrada = ?, Fecha_salida = ?, Valor = ?, Forma_pago = ? WHERE ID = ?");
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
                    "DELETE FROM RESERVAS WHERE ID = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
