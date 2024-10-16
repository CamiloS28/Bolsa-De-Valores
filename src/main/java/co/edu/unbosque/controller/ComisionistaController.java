package co.edu.unbosque.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Comisionista;
import co.edu.unbosque.repository.ComisionistaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ComisionistaController {

	@Autowired
	private ComisionistaRepository comisionistaRepo;

	@PostMapping("/comisionista")
	public ResponseEntity<Comisionista> addComisionista(@RequestParam String empresa, @RequestParam Double comision,
			@RequestParam String pais, @RequestParam Integer usuarioId) {

		Comisionista comisionista = new Comisionista();
		comisionista.setEmpresa(empresa);
		comisionista.setComision(comision);
		comisionista.setPais(pais);
		comisionista.setUsuario_id(usuarioId);

		comisionistaRepo.save(comisionista);
		return ResponseEntity.status(HttpStatus.CREATED).body(comisionista);
	}

	@GetMapping("/comisionista")
	public ResponseEntity<List<Comisionista>> getAllComisionistas() {
		List<Comisionista> comisionistas = comisionistaRepo.findAll();
		if (comisionistas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(comisionistas);
	}

	@GetMapping("/comisionista/{id}")
	public ResponseEntity<Comisionista> getComisionistaById(@PathVariable Integer id) {
		Optional<Comisionista> comisionista = comisionistaRepo.findById(id);
		if (comisionista.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(comisionista.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping("/comisionista/{id}")
	public ResponseEntity<Comisionista> updateComisionista(@PathVariable Integer id, @RequestParam String empresa,
			@RequestParam Double comision, @RequestParam String pais, @RequestParam Integer usuarioId) {

		Optional<Comisionista> comisionistaOptional = comisionistaRepo.findById(id);

		if (!comisionistaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Comisionista comisionista = comisionistaOptional.get();
		comisionista.setEmpresa(empresa);
		comisionista.setComision(comision);
		comisionista.setPais(pais);
		comisionista.setUsuario_id(usuarioId);

		comisionistaRepo.save(comisionista);
		return ResponseEntity.status(HttpStatus.OK).body(comisionista);
	}

	@DeleteMapping("/comisionista/{id}")
	public ResponseEntity<String> deleteComisionista(@PathVariable Integer id) {
		Optional<Comisionista> comisionistaOptional = comisionistaRepo.findById(id);

		if (!comisionistaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		comisionistaRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Comisionista eliminado exitosamente");
	}
}
