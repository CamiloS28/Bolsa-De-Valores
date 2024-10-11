package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
public class Portafolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer portafolio_id;

    @ManyToOne
    @JoinColumn(name = "inversionista_id", nullable = false)
    private Inversionista inversionista;

    @ManyToOne
    @JoinColumn(name = "accion_id", nullable = false)
    private Accion accion;

    private Integer cantidad;

    private Double valor_total;

    public Portafolio() {
    }

    public Portafolio(Inversionista inversionista, Accion accion, Integer cantidad, Double valor_total) {
        this.inversionista = inversionista;
        this.accion = accion;
        this.cantidad = cantidad;
        this.valor_total = valor_total;
    }

    // Getters y Setters
    public Integer getPortafolio_id() {
        return portafolio_id;
    }

    public void setPortafolio_id(Integer portafolio_id) {
        this.portafolio_id = portafolio_id;
    }

    public Inversionista getInversionista() {
        return inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

}
