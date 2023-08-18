package tests;

import factory.ConexionFactory;

import java.sql.Connection;

public class ConexionPrueba {
    public static void main(String[] args) {
        ConexionFactory conexionFactory = new ConexionFactory();

        for (int i = 0; i < 20; i++) {
            Connection connection = conexionFactory.getConexion();
            System.out.println("Conexión # " + i + " exitosa");

        }
    }
}
