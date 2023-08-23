package model;

public class InfoSession {
    private static Usuario usuarioLogueado = null;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }
}
