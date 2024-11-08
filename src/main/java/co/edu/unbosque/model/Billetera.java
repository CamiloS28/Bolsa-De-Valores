package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Billetera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billetera_id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Double saldo;

    public Billetera() {
    }

    public Billetera(Usuario usuario, Double saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public Integer getBilletera_id() {
        return billetera_id;
    }

    public void setBilletera_id(Integer billetera_id) {
        this.billetera_id = billetera_id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
