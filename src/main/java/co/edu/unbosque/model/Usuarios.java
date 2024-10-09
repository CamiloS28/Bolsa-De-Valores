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
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String contrasena;
    @Getter
    @Setter
    private String rol;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_creacion;

    public Usuarios() {
        // TODO Auto-generated constructor stub
    }

    public Usuarios(String nombre, String email, String contrasena, String rol, Date fecha_creacion) {
        super();
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fecha_creacion = fecha_creacion;
    }

    public Usuarios(Integer id, String nombre, String email, String contrasena, String rol, Date fecha_creacion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fecha_creacion = fecha_creacion;
    }

}
