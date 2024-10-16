package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Inversionista {

    @Id
    private Integer inversionista_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "inversionista_id")
    private Usuario usuario;

    private String perfil_riesgo;
    private String pais;

    public Inversionista() {
    }

    public Inversionista(Usuario usuario, String perfil_riesgo, String pais) {
        this.usuario = usuario;
        this.perfil_riesgo = perfil_riesgo;
        this.pais = pais;
    }

    // Getters y Setters
    public Integer getInversionista_id() {
        return inversionista_id;
    }

    public void setInversionista_id(Integer inversionista_id) {
        this.inversionista_id = inversionista_id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPerfil_riesgo() {
        return perfil_riesgo;
    }

    public void setPerfil_riesgo(String perfil_riesgo) {
        this.perfil_riesgo = perfil_riesgo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
