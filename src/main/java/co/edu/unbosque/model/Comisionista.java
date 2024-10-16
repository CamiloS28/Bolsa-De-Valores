package co.edu.unbosque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Comisionista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer comisionista_id;

	@Column(nullable = false)
	private String empresa;

	@Column(nullable = false)
	private Double comision;

	@Column(nullable = false)
	private String pais;

	@Column(nullable = false)
	private Integer usuario_id;


	public Comisionista() {
	}

	public Comisionista(Integer comisionista_id, String empresa, Double comision, String pais, Integer usuario_id) {
		super();
		this.comisionista_id = comisionista_id;
		this.empresa = empresa;
		this.comision = comision;
		this.pais = pais;
		this.usuario_id = usuario_id;
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

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

}
