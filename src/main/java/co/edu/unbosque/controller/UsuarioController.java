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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usrdao;
	public ArrayList<Usuario> iniciar = new ArrayList<Usuario>();
	public int variante = 0;

	@PostMapping(path = "/usuario")
	public ResponseEntity<Usuario> add(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contraseña, @RequestParam String rol,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_creacion) {

		List<Usuario> all = (List<Usuario>) usrdao.findAll();

		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getNombre().equals(nombre) && all.get(i).getEmail().equals(email)
					&& all.get(i).getContraseña().equals(contraseña) && all.get(i).getRol().equals(rol)
					&& all.get(i).getFecha_creacion().equals(fecha_creacion)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}

		Usuario uc = new Usuario();
		uc.setNombre(nombre);
		uc.setEmail(email);
		uc.setContraseña(contraseña);
		uc.setRol(rol);
		uc.setFecha_creacion(fecha_creacion);
		usrdao.save(uc);

		if (variante == 0) {
			iniciar.add(uc);
			variante++;
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(uc);
	}

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> mostrarTodo() {
		List<Usuario> lista = usrdao.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/login")
	public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String contraseña) {
		List<Usuario> all = (List<Usuario>) usrdao.findAll();

		Usuario foundUsuario = null;

		if (all.get(0).getEmail().equals(email) && all.get(0).getContraseña().equals(contraseña)) {
			// admin
			foundUsuario = all.get(0);
			if (variante == 0) {
				iniciar.add(foundUsuario);
				variante++;
			}
		}

		for (int i = 1; i < all.size(); i++) {
			if (all.get(i).getEmail().equals(email) && all.get(i).getContraseña().equals(contraseña)) {
				foundUsuario = all.get(i);
				if (variante == 0) {
					iniciar.add(foundUsuario);
					variante++;
				}
				break;
			}
		}

		if (foundUsuario != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundUsuario);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/inicio")
	public ResponseEntity<Usuario> inicio() {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(iniciar.get(0));

	}

	@GetMapping("/cerrar")
	public RedirectView cerrar() {

		iniciar.clear();
		variante = 0;

		String url = "http://localhost:8080/Frontend/login.html";
		return new RedirectView(url);

	}

	@GetMapping("/usuarioExistentes")
	public ResponseEntity<String> getExists() {
		List<Usuario> all = (List<Usuario>) usrdao.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(all.size() + "");
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getOne(@PathVariable Integer id) {
		Optional<Usuario> op = usrdao.findById(id);
		if (op.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(op.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Optional<Usuario> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		usrdao.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<Boolean> update(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contraseña, @RequestParam String rol,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha_creacion,
			@PathVariable Integer id) {

		Optional<Usuario> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.ok(false);
		}
		return op.map(usr -> {
			usr.setNombre(nombre);
			usr.setEmail(email);
			usr.setContraseña(contraseña);
			usr.setRol(rol);
			usr.setFecha_creacion(fecha_creacion);

			usrdao.save(usr);
			return ResponseEntity.ok(true);
		}).orElseGet(() -> {
			Usuario nuevo = new Usuario();
			nuevo.setId(id);
			nuevo.setNombre(nombre);
			nuevo.setEmail(email);
			nuevo.setContraseña(contraseña);
			nuevo.setRol(rol);
			nuevo.setFecha_creacion(fecha_creacion);
			usrdao.save(nuevo);
			return ResponseEntity.ok(true);
		});
	}

}
