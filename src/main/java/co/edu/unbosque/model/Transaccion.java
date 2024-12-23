package co.edu.unbosque.model;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.TemporalType;
import java.util.Date;
import jakarta.persistence.GenerationType;

@Entity
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transaccion_id;

	@ManyToOne
	@JoinColumn(name = "inversionista_id", nullable = false)
	private Inversionista inversionista;

	@ManyToOne
	@JoinColumn(name = "comisionista_id", nullable = false)
	private Comisionista comisionista;

	private String tipo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	private Double cantidad;

	private Double precio;

	private Double monto_total;

	@ManyToOne
	@JoinColumn(name = "contrato_id", nullable = false)
	private Contrato contrato;

	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)
	private Empresa empresa;

	private boolean estado;

	public Transaccion() {
	}

	public Transaccion(Integer transaccion_id, Inversionista inversionista, Comisionista comisionista, String tipo,
			Date fecha, Double cantidad, Double precio, Double monto_total, Contrato contrato, Empresa empresa,
			boolean estado) {
		super();
		this.transaccion_id = transaccion_id;
		this.inversionista = inversionista;
		this.comisionista = comisionista;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precio = precio;
		this.monto_total = monto_total;
		this.contrato = contrato;
		this.empresa = empresa;
		this.estado = estado;
	}

	// Getters y Setters
	public Integer getTransaccion_id() {
		return transaccion_id;
	}

	public void setTransaccion_id(Integer transaccion_id) {
		this.transaccion_id = transaccion_id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getMonto_total() {
		return monto_total;
	}

	public void setMonto_total(Double monto_total) {
		this.monto_total = monto_total;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


}
