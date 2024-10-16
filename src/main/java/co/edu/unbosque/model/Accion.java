package co.edu.unbosque.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accion_id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    private Double precio_actual;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_actualizacion;

    @ManyToOne
    @JoinColumn(name = "divisa_id", nullable = false)
    private Divisa divisa;

    public Accion() {
    }

    public Accion(Empresa empresa, Double precio_actual, Date fecha_actualizacion, Divisa divisa) {
        this.empresa = empresa;
        this.precio_actual = precio_actual;
        this.fecha_actualizacion = fecha_actualizacion;
        this.divisa = divisa;
    }

    // Getters y Setters
    public Integer getAccion_id() {
        return accion_id;
    }

    public void setAccion_id(Integer accion_id) {
        this.accion_id = accion_id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Double getPrecio_actual() {
        return precio_actual;
    }

    public void setPrecio_actual(Double precio_actual) {
        this.precio_actual = precio_actual;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

}
