package co.edu.unbosque.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.repository.DivisaRepository;
import co.edu.unbosque.service.AccionService;
import co.edu.unbosque.service.DivisaService;
import co.edu.unbosque.model.Divisa;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DivisaController {

	@Autowired
	private DivisaService divisaService;

	public ArrayList<Divisa> divisas = new ArrayList<Divisa>();
	public int variante = 0;

	@PostMapping("/divisa")
	public ResponseEntity<Divisa> add(@RequestParam String nombre, @RequestParam String simbolo,
			@RequestParam Double tasa_cambio) {

		List<Divisa> all = divisaService.getAll();

		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getNombre().equals(nombre) && all.get(i).getSimbolo().equals(simbolo)
					&& all.get(i).getTasa_cambio().equals(tasa_cambio)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}

		Divisa dc = new Divisa();
		dc.setNombre(nombre);
		dc.setSimbolo(simbolo);
		dc.setTasa_cambio(tasa_cambio);
		divisaService.createDivisa(dc);

		if (variante == 0) {
			divisas.add(dc);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dc);

	}

	@GetMapping("/divisa")
	public ResponseEntity<List<Divisa>> mostrarTodo() {

		List<Divisa> listaDivisas = divisaService.getAll();
		if (listaDivisas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaDivisas);
	}

	@DeleteMapping("/divisa/{divisa_id}")
	public ResponseEntity<String> delete(@PathVariable Integer divisa_id) {

		Optional<Divisa> divisaOpt = divisaService.getDivisaById(divisa_id);
		if (!divisaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		divisaService.deleteDivisa(divisa_id);

		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

	@PutMapping("/divisa/{divisa_id}")
	public ResponseEntity<Boolean> update(@RequestParam String nombre, @RequestParam String sigla,
			@RequestParam Double tasa_cambio, @RequestParam Integer divisa_id) {

		Optional<Divisa> divisaOpt = divisaService.getDivisaById(divisa_id);
		if (!divisaOpt.isPresent()) {
			return ResponseEntity.ok(false);
		}
		return divisaOpt.map(div -> {
			div.setNombre(nombre);
			div.setSimbolo(sigla);
			div.setTasa_cambio(tasa_cambio);
			divisaService.createDivisa(div);

			return ResponseEntity.ok(true);
		}).orElseGet(() -> {
			Divisa nuevo = new Divisa();
			nuevo.setDivisa_id(divisa_id);
			nuevo.setNombre(nombre);
			nuevo.setSimbolo(sigla);
			nuevo.setTasa_cambio(tasa_cambio);
			divisaService.createDivisa(nuevo);

			return ResponseEntity.ok(true);
		});

	}
}
