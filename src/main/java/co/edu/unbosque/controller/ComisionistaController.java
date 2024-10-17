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
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

import co.edu.unbosque.model.Comisionista;
import co.edu.unbosque.repository.ComisionistaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ComisionistaController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ComisionistaRepository comisionistaRepo;

	@PostMapping("/comisionista")
	public ResponseEntity<Comisionista> addComisionista(@RequestParam String empresa, @RequestParam Double comision,
			@RequestParam String pais, @RequestParam Integer usuarioId) {

		Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
		if (!usuarioOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Usuario usuario = usuarioOpt.get();
		if (comisionistaRepo.existsById(usuario.getUsuario_id())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}

		Comisionista comisionista = new Comisionista(usuario, empresa, comision, pais);
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
	public ResponseEntity<Boolean> updateComisionista(@PathVariable String empresa,
			@RequestParam Double comision, @RequestParam String pais, @RequestParam Integer comisionista_id) {

		Optional<Comisionista> comisionistaOptional = comisionistaRepo.findById(comisionista_id);

		if (!comisionistaOptional.isPresent()) {
			return ResponseEntity.ok(false);
		}

		return comisionistaOptional.map(comi -> {
			comi.setEmpresa(empresa);
			comi.setComision(comision);
			comi.setPais(pais);
			comisionistaRepo.save(comi);
			return ResponseEntity.ok(true);
		}).orElseGet(() -> {
			Comisionista nuevo = new Comisionista();
			nuevo.setEmpresa(empresa);
			nuevo.setComision(comision);
			nuevo.setPais(pais);
			comisionistaRepo.save(nuevo);
			return ResponseEntity.ok(true);
		});
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
