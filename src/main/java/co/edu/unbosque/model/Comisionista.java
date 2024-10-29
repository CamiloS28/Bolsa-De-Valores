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
public class Comisionista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer comisionista_id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "comisionista_id")
	private Usuario usuario;

	@Column(nullable = false)
	private String empresa;

	@Column(nullable = false)
	private Double comision;

	@Column(nullable = false)
	private String pais;

	public Comisionista() {
	}

	public Comisionista(Usuario usuario, String empresa, Double comision, String pais) {
		this.usuario = usuario;
		this.empresa = empresa;
		this.comision = comision;
		this.pais = pais;
	}

	public Integer getComisionista_id() {
		return comisionista_id;
	}

	public void setComisionista_id(Integer comisionista_id) {
		this.comisionista_id = comisionista_id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Double getComision() {
		return comision;
	}

	public void setComision(Double comision) {
		this.comision = comision;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
