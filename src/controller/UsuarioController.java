package controller;

import DAO.UsuarioDAO;
import factory.ConexionFactory;
import model.Usuario;

public class UsuarioController {
    final private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(new ConexionFactory().getConexion());
    }

    public Usuario login(String usuario, String contrasena) {
        return usuarioDAO.login(usuario, contrasena);
    }
}
