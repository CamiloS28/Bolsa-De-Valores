package co.edu.unbosque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;
import co.edu.unbosque.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(path = "/usuario")
	public ResponseEntity<Usuario> add(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contraseña, @RequestParam String rol,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_creacion) {

		List<Usuario> allUsuarios = usuarioService.getAll();
		for (Usuario usuarioExistente : allUsuarios) {
			if (usuarioExistente.getEmail().equals(email)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setContraseña(contraseña);
		usuario.setRol(rol);
		usuario.setFecha_creacion(fecha_creacion);
		usuarioService.createUsuario(usuario);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
	}

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> mostrarTodo() {
		List<Usuario> lista = (List<Usuario>) usuarioService.getAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/login")
	public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String contraseña) {
		List<Usuario> all = (List<Usuario>) usuarioService.getAll();
		Usuario foundUsuario = null;

		for (Usuario usuario : all) {
			if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(contraseña)) {
				foundUsuario = usuario;
				break;
			}
		}

		if (foundUsuario != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundUsuario); // Devolver el objeto Usuario
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/usuarioExistentes")
	public ResponseEntity<String> getExists() {
		List<Usuario> all = (List<Usuario>) usuarioService.getAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(all.size() + "");
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getOne(@PathVariable Integer id) {
		Optional<Usuario> op = usuarioService.getUsuarioById(id);
		if (op.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(op.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Optional<Usuario> op = usuarioService.getUsuarioById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		usuarioService.deleteUsuario(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> update(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contraseña, @RequestParam String rol,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_creacion, @PathVariable Integer id) {

		Optional<Usuario> op = usuarioService.getUsuarioById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		List<Usuario> allUsuarios = usuarioService.getAll();
		for (Usuario usuarioExistente : allUsuarios) {
			if (usuarioExistente.getEmail().equals(email)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}

		Usuario usuario = new Usuario();

		usuario.setUsuario_id(id);

		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setContraseña(contraseña);
		usuario.setRol(rol);
		usuario.setFecha_creacion(fecha_creacion);
		usuarioService.updateUsuario(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);

	}

	@GetMapping("/emails")
	public ResponseEntity<List<String>> obtenerTodosLosCorreos() {
		List<String> emails = usuarioService.obtenerTodosLosCorreos();

		if (emails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Si no hay correos, devolver un 204 NO CONTENT
		} else {
			return new ResponseEntity<>(emails, HttpStatus.OK); // Si hay correos, devolver la lista con un 200 OK
		}
	}
}
