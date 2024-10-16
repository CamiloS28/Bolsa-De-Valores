package co.edu.unbosque.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Accion;
import co.edu.unbosque.repository.AccionRepository;
import co.edu.unbosque.repository.EmpresaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import co.edu.unbosque.model.Empresa;
import co.edu.unbosque.model.Divisa;
import co.edu.unbosque.repository.DivisaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AccionController {

    @Autowired
    private DivisaRepository divisaRepository;

    @Autowired
    private AccionRepository accionRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping("/accion")
    public ResponseEntity<Accion> add(
            @RequestParam Integer empresa_id,
            @RequestParam Double precio_actual,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_actualizacion,
            @RequestParam Integer divisa_id) {

        // Validar que el precio no sea negativo
        if (precio_actual < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        // Validar que la fecha de actualización no sea en el futuro
        Date hoy = new Date();
        if (fecha_actualizacion.after(hoy)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        Optional<Empresa> empresaOpt = empresaRepository.findById(empresa_id);
        if (!empresaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Optional<Divisa> divisOpt = divisaRepository.findById(divisa_id);
        if (!divisOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Accion accion = new Accion();
        accion.setEmpresa(empresaOpt.get());
        accion.setPrecio_actual(precio_actual);
        accion.setFecha_actualizacion(fecha_actualizacion);
        accion.setDivisa(divisOpt.get());
        accionRepository.save(accion);

        // Devolver la respuesta con la acción creada
        return ResponseEntity.status(HttpStatus.CREATED).body(accion);
    }

    @GetMapping("/accion")
    public ResponseEntity<List<Accion>> mostrarTodo() {
        List<Accion> listaAcciones = accionRepository.findAll();
        if (listaAcciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(listaAcciones);
    }

    @DeleteMapping("/accion/{id}")
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        Optional<Accion> accionOpt = accionRepository.findById(id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("");
        }
        accionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body("Deleted");
    }

    @PutMapping("/accion/{id}")
    public ResponseEntity<Accion> update(@RequestParam Integer empresa_id,
            @RequestParam Double precio_actual,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_actualizacion,
            @RequestParam Integer divisa_id,
            @RequestParam Integer accion_id) {

        Optional<Empresa> empresaOpt = empresaRepository.findById(empresa_id);
        if (!empresaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Optional<Divisa> divisaOpt = divisaRepository.findById(divisa_id);
        if (!divisaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Optional<Accion> accionOpt = accionRepository.findById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return accionOpt.map(acc -> {
            acc.setEmpresa(empresaOpt.get());
            acc.setPrecio_actual(precio_actual);
            acc.setFecha_actualizacion(fecha_actualizacion);
            acc.setDivisa(divisaOpt.get());
            accionRepository.save(acc);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(acc);
        }).orElseGet(() -> {
            Accion accion = new Accion();
            accion.setEmpresa(empresaOpt.get());
            accion.setPrecio_actual(precio_actual);
            accion.setFecha_actualizacion(fecha_actualizacion);
            accion.setDivisa(divisaOpt.get());
            accion.setAccion_id(accion_id);
            accionRepository.save(accion);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(accion);
        });

    }

}