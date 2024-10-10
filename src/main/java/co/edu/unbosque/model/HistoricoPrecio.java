package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
public class HistoricoPrecio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer historico_id;

    @ManyToOne
    @JoinColumn(name = "accion_id", nullable = false)
    private Accion accion;

    private Double precio;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public HistoricoPrecio() {
    }

    public HistoricoPrecio(Accion accion, Double precio, Date fecha) {
        this.accion = accion;
        this.precio = precio;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Integer getHistorico_id() {
        return historico_id;
    }

    public void setHistorico_id(Integer historico_id) {
        this.historico_id = historico_id;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
