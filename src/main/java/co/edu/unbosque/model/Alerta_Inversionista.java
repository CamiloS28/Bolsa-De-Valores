package co.edu.unbosque.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;
import java.util.Date;;

@Entity
public class Alerta_Inversionista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer alerta_id;

    @ManyToOne
    @JoinColumn(name = "inversionista_id", nullable = false)
    private Inversionista inversionista;

    private String tipo_alerta;

    private String condicion;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_creacion;

    public Alerta_Inversionista() {
    }

    public Alerta_Inversionista(Inversionista inversionista, String tipo_alerta, String condicion,
            Date fecha_creacion) {
        this.inversionista = inversionista;
        this.tipo_alerta = tipo_alerta;
        this.condicion = condicion;
        this.fecha_creacion = fecha_creacion;
    }

    // Getters y Setters
    public Integer getAlerta_id() {
        return alerta_id;
    }

    public void setAlerta_id(Integer alerta_id) {
        this.alerta_id = alerta_id;
    }

    public Inversionista getInversionista() {
        return inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}