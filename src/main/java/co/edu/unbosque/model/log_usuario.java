package co.edu.unbosque.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class log_usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer log_id;

    private Integer transaccion_id;

    private Integer usuario_id;

    private String nombre_usuario;

    private String email_usuario;

    private String rol_usuario;

    private String tipo_modificacion;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_modificacion;

    public log_usuario() {
    }

    public log_usuario(Integer transaccion_id, Integer usuario_id, String nombre_usuario, String email_usuario,
            String rol_usuario, String tipo_modificacion, Date fecha_modificacion) {

        this.transaccion_id = transaccion_id;
        this.usuario_id = usuario_id;
        this.nombre_usuario = nombre_usuario;
        this.email_usuario = email_usuario;
        this.rol_usuario = rol_usuario;
        this.tipo_modificacion = tipo_modificacion;
        this.fecha_modificacion = fecha_modificacion;

    }

    public Integer getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(Integer transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getRol_usuario() {
        return rol_usuario;
    }

    public void setRol_usuario(String rol_usuario) {
        this.rol_usuario = rol_usuario;
    }

    public String getTipo_modificacion() {
        return tipo_modificacion;
    }

    public void setTipo_modificacion(String tipo_modificacion) {
        this.tipo_modificacion = tipo_modificacion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

}
