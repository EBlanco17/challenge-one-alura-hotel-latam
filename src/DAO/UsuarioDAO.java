package DAO;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    final private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public Usuario login(String usuario, String contrasena) {
        Usuario resultado = null;
        try(con){
            final PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?");

            try(statement){
                statement.setString(1, usuario);
                statement.setString(2, contrasena);

                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado = new Usuario();
                        resultado.setId(resultSet.getInt("ID"));
                        resultado.setUsuario(resultSet.getString("USUARIO"));
                        resultado.setContrasena(resultSet.getString("CONTRASENA"));
                        resultado.setNombre(resultSet.getString("NOMBRE"));
                        resultado.setApellido(resultSet.getString("APELLIDO"));
                        resultado.setEmail(resultSet.getString("EMAIL"));
                        resultado.setTelefono(resultSet.getString("TELEFONO"));

                    }
                }
                return resultado;
            }

        }catch(SQLException e){
            return null;
        }
    }
}
