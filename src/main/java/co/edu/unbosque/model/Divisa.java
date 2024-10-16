package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity
public class Divisa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer divisa_id;

    private String nombre;

    private String simbolo;

    private Double tasa_cambio;

    public Divisa() {
    }

    public Divisa(String nombre, String simbolo, Double tasa_cambio) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.tasa_cambio = tasa_cambio;
    }

    // Getters y Setters
    public Integer getDivisa_id() {
        return divisa_id;
    }

    public void setDivisa_id(Integer divisa_id) {
        this.divisa_id = divisa_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Double getTasa_cambio() {
        return tasa_cambio;
    }

    public void setTasa_cambio(Double tasa_cambio) {
        this.tasa_cambio = tasa_cambio;
    }

}
