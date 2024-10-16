package co.edu.unbosque.controller;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.unbosque.repository.HistoricoPrecioRepository;
import co.edu.unbosque.repository.AccionRepository;
import co.edu.unbosque.model.HistoricoPrecio;
import co.edu.unbosque.model.Accion;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.format.annotation.DateTimeFormat;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HistoricoPrecioController {

    @Autowired
    private HistoricoPrecioRepository historicoPrecioRepository;

    @Autowired
    private AccionRepository accionRepository;

    @PostMapping("/historico_precio")
    public ResponseEntity<HistoricoPrecio> add(@RequestParam Integer accion_id, @RequestParam Double precio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {

        Optional<Accion> accionOpt = accionRepository.findById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        HistoricoPrecio historicoPrecio = new HistoricoPrecio();
        historicoPrecio.setAccion(accionOpt.get());
        historicoPrecio.setPrecio(precio);
        historicoPrecio.setFecha(fecha);

        historicoPrecioRepository.save(historicoPrecio);

        return ResponseEntity.status(HttpStatus.CREATED).body(historicoPrecio);
    }

    @GetMapping("/historico_precio")
    public ResponseEntity<HistoricoPrecio> mostrarTodo() {

        List<HistoricoPrecio> listaHistoricoPrecio = historicoPrecioRepository.findAll();
        if (listaHistoricoPrecio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaHistoricoPrecio.get(0));
    }

    @DeleteMapping("/historico_precio/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<HistoricoPrecio> historicoPrecioOpt = historicoPrecioRepository.findById(id);
        if (!historicoPrecioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        historicoPrecioRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/historico_precio/{id}")
    public ResponseEntity<HistoricoPrecio> update(@RequestParam Integer accion_id, @RequestParam Double precio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @PathVariable Integer id) {

        Optional<Accion> accionOpt = accionRepository.findById(accion_id);
        if (!accionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<HistoricoPrecio> historicoPrecioOpt = historicoPrecioRepository.findById(id);
        if (!historicoPrecioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return historicoPrecioOpt.map(hp -> {
            hp.setAccion(accionOpt.get());
            hp.setPrecio(precio);
            hp.setFecha(fecha);
            historicoPrecioRepository.save(hp);
            return ResponseEntity.status(HttpStatus.OK).body(hp);
        }).orElseGet(() -> {
            HistoricoPrecio historicoPrecio = new HistoricoPrecio();
            historicoPrecio.setAccion(accionOpt.get());
            historicoPrecio.setPrecio(precio);
            historicoPrecio.setFecha(fecha);
            historicoPrecioRepository.save(historicoPrecio);
            return ResponseEntity.status(HttpStatus.OK).body(historicoPrecio);
        });
    }
}
