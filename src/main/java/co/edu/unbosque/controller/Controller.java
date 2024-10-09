package co.edu.unbosque.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@org.springframework.stereotype.Controller

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/")
    public String inicio(Model modelo) {
        return "index";
    }
    
	@GetMapping("/usuario")
	public void mostrarTodo() {
		System.out.println("xd");
	}
	
	@PostMapping(path = "/usuario")
	public ResponseEntity<String> add(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contrasena) {


		return ResponseEntity.status(HttpStatus.FOUND).body("Post");
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {


		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<String> update(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contrasena, @PathVariable Integer id) {

		return ResponseEntity.status(HttpStatus.FOUND).body("put");

		
	}
}