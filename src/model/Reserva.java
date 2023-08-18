package model;

import java.sql.Date;

public class Reserva {
    private Integer id;

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

    private Date fechaEntrada;
    private Date fechaSalida;
    private Double valor;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, Double valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
}
