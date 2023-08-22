package controller;

import DAO.HuespedDAO;
import factory.ConexionFactory;
import model.Huesped;

import java.util.List;

public class HuespedController {
    final private  HuespedDAO huespedDAO;

    public HuespedController() {

        this.huespedDAO = new HuespedDAO(new ConexionFactory().getConexion());
    }
    public int insertarHuesped(Huesped huesped) {
        return huespedDAO.insertarHuesped(huesped);
    }

    public List<Huesped> listar() {
        return huespedDAO.listar();
    }

    public int modificar(Huesped huesped) {
        return huespedDAO.modificar(huesped);
    }

    public int eliminar(Integer id) {
        return huespedDAO.eliminar(id);
    }

    public void actualizarReservaId(Integer idReserva) {
        huespedDAO.actualizarReserva(idReserva);
    }

}
