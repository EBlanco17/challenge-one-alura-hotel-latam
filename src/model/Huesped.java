package model;
import java.sql.Date;
import java.util.List;

public class Huesped {
    private Integer id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer idReserva;

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

}
