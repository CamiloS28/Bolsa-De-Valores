package co.edu.unbosque.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.log_usuario;
import co.edu.unbosque.service.log_usuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class log_usuarioController {

    @Autowired
    private log_usuarioService log_usuarioService;

    @PostMapping("/log_usuario")
    public ResponseEntity<log_usuario> add(@RequestParam Integer transaccion_id, @RequestParam Integer usuario_id,
            @RequestParam String nombre_usuario, @RequestParam String email_usuario, @RequestParam String rol_usuario,
            @RequestParam String tipo_modificacion,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_modificacion) {

        Optional<log_usuario> optionalLogUsuario = log_usuarioService.getlog_usuarioById(transaccion_id);

        if (!optionalLogUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log_usuario LogUsuario = new log_usuario();
        LogUsuario.setTransaccion_id(transaccion_id);
        LogUsuario.setUsuario_id(usuario_id);
        LogUsuario.setNombre_usuario(nombre_usuario);
        LogUsuario.setEmail_usuario(email_usuario);
        LogUsuario.setRol_usuario(rol_usuario);
        LogUsuario.setTipo_modificacion(tipo_modificacion);
        LogUsuario.setFecha_modificacion(fecha_modificacion);

        log_usuarioService.createlog_usuario(LogUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(LogUsuario);

    }

    @PutMapping("/log_usuario{id}")
    public ResponseEntity<log_usuario> updateLog_Usuario(@PathVariable Integer id, @RequestParam Integer transaccion_id,
            @RequestParam Integer usuario_id,
            @RequestParam String nombre_usuario, @RequestParam String email_usuario, @RequestParam String rol_usuario,
            @RequestParam String tipo_modificacion,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_modificacion) {

        Optional<log_usuario> optionalLogUsuario = log_usuarioService.getlog_usuarioById(id);

        if (!optionalLogUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log_usuario log_usuario = new log_usuario();
        log_usuario.setTransaccion_id(transaccion_id);
        log_usuario.setUsuario_id(usuario_id);
        log_usuario.setNombre_usuario(nombre_usuario);
        log_usuario.setEmail_usuario(email_usuario);
        log_usuario.setRol_usuario(rol_usuario);
        log_usuario.setTipo_modificacion(tipo_modificacion);
        log_usuario.setFecha_modificacion(fecha_modificacion);

        log_usuarioService.updatelog_usuario(log_usuario);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(log_usuario);

    }

    @GetMapping("/log_usuario")
    public ResponseEntity<List<log_usuario>> getLog_Usuarios() {
        List<log_usuario> logUsuarios = log_usuarioService.getAll();
        if (logUsuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Cambiar a NO_CONTENT si la lista está vacía
        }
        return ResponseEntity.ok(logUsuarios); // Retornar la lista completa con un status OK
    }

    @DeleteMapping("/log_usuario/{id}")
    public ResponseEntity<log_usuario> deleteLog_Usuario(@PathVariable Integer id) {

        Optional<log_usuario> log_usuario = log_usuarioService.getlog_usuarioById(id);

        if (!log_usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log_usuarioService.deletelog_usuario(id);

        return ResponseEntity.ok().build();

    }
}
