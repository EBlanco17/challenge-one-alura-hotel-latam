package controller;

import DAO.ReservaDAO;
import factory.ConexionFactory;
import model.Reserva;

public class ReservaController {
    final private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConexionFactory().getConexion());
    }

    public void insertarReserva(Reserva reserva) {
        reservaDAO.insertarReserva(reserva);
    }
}
