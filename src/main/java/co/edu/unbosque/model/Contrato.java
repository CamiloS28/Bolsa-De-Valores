package co.edu.unbosque.model;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import jakarta.persistence.GenerationType;

@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contrato_id;

    @ManyToOne
    @JoinColumn(name = "inversionista_id", nullable = false)
    private Inversionista inversionista;

    @ManyToOne
    @JoinColumn(name = "comisionista_id", nullable = false)
    private Comisionista comisionista;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_firma;

    private double monto_inicial;

    private String condiciones;

    private boolean estado;

    public Contrato() {
    }

    public Contrato(Inversionista inversionista, Comisionista comisionista, Date fecha_firma, double monto_inicial,
            String condiciones, boolean estado) {
        this.inversionista = inversionista;
        this.comisionista = comisionista;
        this.fecha_firma = fecha_firma;
        this.monto_inicial = monto_inicial;
        this.condiciones = condiciones;
        this.estado = estado;

    }

    public Integer getContrato_id() {
        return contrato_id;
    }

    public void setContrato_id(Integer contrato_id) {
        this.contrato_id = contrato_id;
    }

    public Inversionista getInversionista() {
        return inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public Comisionista getComisionista() {
        return comisionista;
    }

    public void setComisionista(Comisionista comisionista) {
        this.comisionista = comisionista;
    }

    public Date getFecha_firma() {
        return fecha_firma;
    }

    public void setFecha_firma(Date fecha_firma) {
        this.fecha_firma = fecha_firma;
    }

    public double getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(double monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
