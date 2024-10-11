package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Alerta_Inversionista;
import co.edu.unbosque.repository.Alerta_InversionistaRepository;
import co.edu.unbosque.repository.InversionistaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import java.util.Date;
import java.util.Optional;
import co.edu.unbosque.model.Inversionista;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class Alerta_InversionistaController {

    @Autowired
    private Alerta_InversionistaRepository alerta_inversionistaRepository;

    @Autowired
    private InversionistaRepository inversionistaRepository;

    @PostMapping("/alerta_inversionista")
    public ResponseEntity<Alerta_Inversionista> add(@RequestParam Integer inversionista_id,
            @RequestParam String tipo_alerta, @RequestParam String Condicion,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_creacion) {

        Optional<Inversionista> inversionistaOpt = inversionistaRepository.findById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Alerta_Inversionista alerta_inversionista = new Alerta_Inversionista();
        alerta_inversionista.setInversionista(inversionistaOpt.get());
        alerta_inversionista.setTipo_alerta(tipo_alerta);
        alerta_inversionista.setCondicion(Condicion);
        alerta_inversionista.setFecha_creacion(fecha_creacion);

        alerta_inversionistaRepository.save(alerta_inversionista);

        return ResponseEntity.status(HttpStatus.CREATED).body(alerta_inversionista);

    }

    @GetMapping("/alerta_inversionista")
    public ResponseEntity<List<Alerta_Inversionista>> mostrarTodo() {
        List<Alerta_Inversionista> listaAlerta_Inversionista = alerta_inversionistaRepository.findAll();
        if (listaAlerta_Inversionista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(listaAlerta_Inversionista);
    }

    @DeleteMapping("/alerta_inversionista/{id}")
    public ResponseEntity<Alerta_Inversionista> delete(@PathVariable Integer id) {
        if (!alerta_inversionistaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        alerta_inversionistaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/alerta_inversionista/{id}")
    public ResponseEntity<Alerta_Inversionista> updateAlerta(
            @PathVariable Integer id,
            @RequestParam Integer inversionista_id,
            @RequestParam String tipo_alerta,
            @RequestParam String condicion,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_creacion) {

        // Buscar la alerta por su ID
        Optional<Alerta_Inversionista> alerta_inversionistaOpt = alerta_inversionistaRepository.findById(id);
        if (!alerta_inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Buscar el inversionista por su ID
        Optional<Inversionista> inversionistaOpt = inversionistaRepository.findById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Actualizar los datos de la alerta
        Alerta_Inversionista alertaInversionista = alerta_inversionistaOpt.get();
        alertaInversionista.setTipo_alerta(tipo_alerta);
        alertaInversionista.setCondicion(condicion);
        alertaInversionista.setFecha_creacion(fecha_creacion);
        alertaInversionista.setInversionista(inversionistaOpt.get());

        // Guardar los cambios
        alerta_inversionistaRepository.save(alertaInversionista);
        return ResponseEntity.status(HttpStatus.OK).body(alertaInversionista);
    }
}
