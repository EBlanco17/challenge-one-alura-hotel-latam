package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private Integer id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Double valor;
    private String formaPago;
    private List<Huesped> huespedes;
    public Reserva() {

    }

    public Reserva(int id, Date fechaEntrada, Date fechaSalida, double valor, String formaPago) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Reserva(Date fechaEntrada, Date fechaSalida, Double valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    public Integer getId() {
        return id;
    }
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Double getValor() {
        return valor;
    }

    public String getFormaPago() {
        return formaPago;
    }



    public void agregarHuesped(Huesped huesped) {
        if (this.huespedes == null) {
            this.huespedes = new ArrayList<>();
        }
        this.huespedes.add(huesped);
    }

    public List<Huesped> getHuespedes() {
        return this.huespedes;
    }
}
