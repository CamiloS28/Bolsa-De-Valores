package co.edu.unbosque.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Integer usuario_id;
	@Getter
	@Setter
	private String nombre;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String contraseña;
	@Getter
	@Setter
	private String rol;
	@Getter
	@Setter
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_creacion;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String email, String contraseña, String rol, Date fecha_creacion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
		this.rol = rol;
		this.fecha_creacion = fecha_creacion;
	}

	public Usuario(Integer usuario_id, String nombre, String email, String contraseña, String rol,
			Date fecha_creacion) {
		super();
		this.usuario_id = usuario_id;
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
		this.rol = rol;
		this.fecha_creacion = fecha_creacion;
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

}
