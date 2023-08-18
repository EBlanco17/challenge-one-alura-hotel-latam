package controller;

import DAO.HuespedDAO;
import factory.ConexionFactory;
import model.Huesped;

public class HuespedController {
    final private  HuespedDAO huespedDAO;

    public HuespedController() {

        this.huespedDAO = new HuespedDAO(new ConexionFactory().getConexion());
    }
    public void insertarHuesped(Huesped huesped) {

        huespedDAO.insertarHuesped(huesped);
    }
}
