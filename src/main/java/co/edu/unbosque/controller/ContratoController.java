package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.model.Contrato;
import co.edu.unbosque.model.Inversionista;
import co.edu.unbosque.model.Comisionista;
import co.edu.unbosque.repository.ContratoRepository;
import co.edu.unbosque.repository.InversionistaRepository;
import co.edu.unbosque.repository.ComisionistaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ContratoController {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private InversionistaRepository inversionistaRepository;

    @Autowired
    private ComisionistaRepository comisionistaRepository;

    @PostMapping(path = "/contrato")
    public ResponseEntity<Contrato> add(@RequestParam Integer inversionista_id, @RequestParam Integer comisionista_id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_firma, @RequestParam double monto_inicial,
            @RequestParam String condiciones, @RequestParam boolean estado) {

        Optional<Inversionista> inversionista = inversionistaRepository.findById(inversionista_id);
        Optional<Comisionista> comisionista = comisionistaRepository.findById(comisionista_id);

        if (!inversionista.isPresent() || !comisionista.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Contrato contrato = new Contrato();
        contrato.setInversionista(inversionista.get());
        contrato.setComisionista(comisionista.get());
        contrato.setFecha_firma(fecha_firma);
        contrato.setMonto_inicial(monto_inicial);
        contrato.setCondiciones(condiciones);
        contrato.setEstado(estado);

        contratoRepository.save(contrato);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contrato);
    }

    @GetMapping("/contrato")
    public ResponseEntity<Iterable<Contrato>> mostrarTodo() {

        List<Contrato> lista = contratoRepository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
    }


    @DeleteMapping("/contrato/{id}")
    public ResponseEntity<Contrato> delete(@PathVariable Integer id) {
        Optional<Contrato> contrato = contratoRepository.findById(id);
        if (!contrato.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        contratoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contrato.get());
    }

    @PutMapping("/contrato/{id}")
    public ResponseEntity<Contrato> update(@RequestParam Integer inversionista_id, @RequestParam Integer comisionista_id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_firma, @RequestParam double monto_inicial,
            @RequestParam String condiciones, @RequestParam boolean estado, @PathVariable Integer id) {

        Optional<Contrato> contrato = contratoRepository.findById(id);
        Optional<Inversionista> inversionista = inversionistaRepository.findById(inversionista_id);
        Optional<Comisionista> comisionista = comisionistaRepository.findById(comisionista_id);

        if (!contrato.isPresent() || !inversionista.isPresent() || !comisionista.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        contrato.get().setInversionista(inversionista.get());
        contrato.get().setComisionista(comisionista.get());
        contrato.get().setFecha_firma(fecha_firma);
        contrato.get().setMonto_inicial(monto_inicial);
        contrato.get().setCondiciones(condiciones);
        contrato.get().setEstado(estado);

        contratoRepository.save(contrato.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contrato.get());
    }

}
