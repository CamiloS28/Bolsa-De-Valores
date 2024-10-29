package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empresa_id;

    private String nombre;

    private String sector;

    private String pais;

    private Double valor_mercado;

    public Empresa() {
    }

    public Empresa(String nombre, String sector, String pais, Double valor_mercado) {
        this.nombre = nombre;
        this.sector = sector;
        this.pais = pais;
        this.valor_mercado = valor_mercado;
    }

    // Getters y Setters
    public Integer getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(Integer empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getValor_mercado() {
        return valor_mercado;
    }

    public void setValor_mercado(Double valor_mercado) {
        this.valor_mercado = valor_mercado;
    }

}
