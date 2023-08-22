package controller;

import DAO.ReservaDAO;
import factory.ConexionFactory;
import model.Reserva;

import java.util.List;

public class ReservaController {
    final private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConexionFactory().getConexion());
    }

    public int insertarReserva(Reserva reserva) {
        return reservaDAO.insertarReserva(reserva);
    }

    public List<Reserva> listar() {
        return reservaDAO.listar();
    }
    public int eliminar(Integer id) {
        HuespedController huespedController = new HuespedController();
        huespedController.actualizarReservaId(id);
        return reservaDAO.eliminar(id);
    }

    public int modificar(Reserva reserva) {
        return reservaDAO.modificar(reserva);
    }
    public List<Reserva> buscar(Integer id) {
        return reservaDAO.buscar(id);
    }
    public List<Reserva> buscar(String apellido) {
        return reservaDAO.buscar(apellido);
    }

}
