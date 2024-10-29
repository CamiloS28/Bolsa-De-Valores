package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Portafolio;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import co.edu.unbosque.service.AccionService;
import co.edu.unbosque.service.InversionistaService;
import co.edu.unbosque.service.PortafolioService;
import co.edu.unbosque.model.Inversionista;
import co.edu.unbosque.model.Accion;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PortafolioController {

    @Autowired
    private PortafolioService portafolioService;

    @Autowired
    private InversionistaService inversionistaService;

    @Autowired
    private AccionService accionService;

    @PostMapping("/portafolio")
    public ResponseEntity<Portafolio> add(@RequestParam Integer inversionista_id, @RequestParam Integer accion_id,
            @RequestParam Integer cantidad, @RequestParam Double valor_total) {

        Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Accion> accionOpt = accionService.getAccionById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Portafolio portafolio = new Portafolio();
        portafolio.setInversionista(inversionistaOpt.get());
        portafolio.setAccion(accionOpt.get());
        portafolio.setCantidad(cantidad);
        portafolio.setValor_total(valor_total);

        portafolioService.createPortafolio(portafolio);
        return ResponseEntity.status(HttpStatus.CREATED).body(portafolio);

    }

    @GetMapping("/portafolio")
    public ResponseEntity<Portafolio> mostrarTodo() {

        List<Portafolio> listaPortafolio = portafolioService.getAll();
        if (listaPortafolio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaPortafolio.get(0));
    }

    @DeleteMapping("/portafolio/{id}")
    public ResponseEntity<String> delete(@RequestParam Integer id) {

        Optional<Portafolio> portafolioOpt = portafolioService.getPortafolioById(id);
        if (!portafolioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        portafolioService.deletePortafolio(id);
        return ResponseEntity.status(HttpStatus.OK).body("Portafolio eliminado");
    }

    @PutMapping("/portafolio/{id}")
    public ResponseEntity<Portafolio> update(@PathVariable Integer id, @RequestParam Integer inversionista_id,
            @RequestParam Integer accion_id,
            @RequestParam Integer cantidad, @RequestParam Double valor_total) {

        Optional<Portafolio> portafolioOpt = portafolioService.getPortafolioById(id);
        if (!portafolioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Accion> accionOpt = accionService.getAccionById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Portafolio portafolio = portafolioOpt.get();
        portafolio.setInversionista(inversionistaOpt.get());
        portafolio.setAccion(accionOpt.get());
        portafolio.setCantidad(cantidad);
        portafolio.setValor_total(valor_total);

        portafolioService.createPortafolio(portafolio);
        return ResponseEntity.status(HttpStatus.OK).body(portafolio);

    }

}
