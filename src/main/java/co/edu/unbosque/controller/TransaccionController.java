package co.edu.unbosque.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.repository.TransaccionRepository;
import co.edu.unbosque.repository.InversionistaRepository;
import co.edu.unbosque.repository.AccionRepository;
import co.edu.unbosque.repository.ComisionistaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import co.edu.unbosque.model.Transaccion;
import co.edu.unbosque.model.Inversionista;
import co.edu.unbosque.model.Accion;
import co.edu.unbosque.model.Comisionista;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TransaccionController {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private InversionistaRepository inversionistaRepository;

    @Autowired
    private AccionRepository accionRepository;

    @Autowired
    private ComisionistaRepository comisionistaRepository;

    @PostMapping("/transaccion")
    public ResponseEntity<Transaccion> add(@RequestParam Integer inversionista_id, @RequestParam Integer accion_id,
            @RequestParam Integer comisionista_id, @RequestParam String tipo,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam Double cantidad,
            @RequestParam Double precio,
            @RequestParam Double monto_total) {

        Optional<Inversionista> inversionistaOpt = inversionistaRepository.findById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Accion> accionOpt = accionRepository.findById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Comisionista> comisionistaOpt = comisionistaRepository.findById(comisionista_id);
        if (!comisionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setInversionista(inversionistaOpt.get());
        transaccion.setAccion(accionOpt.get());
        transaccion.setComisionista(comisionistaOpt.get());
        transaccion.setTipo(tipo);
        transaccion.setCantidad(cantidad);
        transaccion.setPrecio(precio);
        transaccion.setFecha(fecha);
        transaccion.setMonto_total(monto_total);

        transaccionRepository.save(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaccion);

    }

    @GetMapping("/transaccion")
    public ResponseEntity<Transaccion> mostrarTodo() {

        List<Transaccion> listaTransaccion = transaccionRepository.findAll();
        if (listaTransaccion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaTransaccion.get(0));
    }

    @GetMapping("/transaccion/{id}")
    public ResponseEntity<Transaccion> mostrarPorId(@PathVariable Integer id) {

        Optional<Transaccion> transaccionOpt = transaccionRepository.findById(id);
        if (!transaccionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(transaccionOpt.get());

    }

    @DeleteMapping("/transaccion/{id}")
    public ResponseEntity<String> delete(@RequestParam Integer id) {

        Optional<Transaccion> transaccionOpt = transaccionRepository.findById(id);
        if (!transaccionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        transaccionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Transaccion eliminada");
    }

    @PutMapping("/transaccion/{id}")
    public ResponseEntity<Transaccion> update(@PathVariable Integer id, @RequestParam Integer inversionista_id,
            @RequestParam Integer accion_id, @RequestParam Integer comisionista_id,
            @RequestParam String tipo, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam Double cantidad, @RequestParam Double precio, @RequestParam Double monto_total) {

        Optional<Transaccion> transaccionOpt = transaccionRepository.findById(id);
        if (!transaccionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Inversionista> inversionistaOpt = inversionistaRepository.findById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Accion> accionOpt = accionRepository.findById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Comisionista> comisionistaOpt = comisionistaRepository.findById(comisionista_id);
        if (!comisionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Transaccion transaccion = transaccionOpt.get();
        transaccion.setInversionista(inversionistaOpt.get());
        transaccion.setAccion(accionOpt.get());
        transaccion.setComisionista(comisionistaOpt.get());
        transaccion.setTipo(tipo);
        transaccion.setCantidad(cantidad);
        transaccion.setPrecio(precio);
        transaccion.setFecha(fecha);
        transaccion.setMonto_total(monto_total);

        transaccionRepository.save(transaccion);
        return ResponseEntity.status(HttpStatus.OK).body(transaccion);

    }
}
