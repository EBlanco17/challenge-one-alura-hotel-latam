package DAO;

import model.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservaDAO {
    final private Connection con;

    public ReservaDAO(Connection con){
        this.con = con;
    }

    public void insertarReserva(Reserva reserva) {
        try (con){
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO RESERVAS (Fecha_entrada, Fecha_salida, Valor, Forma_pago) " +
                            "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try(statement){
                ejecutarRegistro(reserva, statement);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void ejecutarRegistro(Reserva reserva, PreparedStatement statement) {
        try{
            statement.setDate(1, reserva.getFechaEntrada());
            statement.setDate(2, reserva.getFechaSalida());
            statement.setDouble(3, reserva.getValor());
            statement.setString(4, reserva.getFormaPago());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
