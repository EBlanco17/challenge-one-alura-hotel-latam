package DAO;

import model.Huesped;

import java.sql.*;

public class HuespedDAO {
    final private Connection con;

    public HuespedDAO(Connection con){
        this.con = con;
    }

    public void insertarHuesped(Huesped huesped){
        try(con){
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO HUESPEDES (Nombre, Apellido, Fecha_nacimiento, Nacionalidad, Telefono, id_reserva) " +
                            "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try(statement){
                ejecutarRegistro(huesped, statement);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void ejecutarRegistro(Huesped huesped, PreparedStatement statement) {
        try{
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());
            statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento().toString()));
            statement.setString(4, huesped.getNacionalidad());
            statement.setString(5, huesped.getTelefono());
            statement.setInt(6, huesped.getIdReserva());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
