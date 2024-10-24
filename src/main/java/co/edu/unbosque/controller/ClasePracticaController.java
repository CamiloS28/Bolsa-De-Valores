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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.ClasePractica;
import co.edu.unbosque.service.ClasePracticaService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clase-practica") // Adjust path as needed
public class ClasePracticaController {

	@Autowired
	private ClasePracticaService service;

	@GetMapping
	public List<ClasePractica> getAllClasesPracticas() {
		return service.getAllClasesPracticas();
	}

	@GetMapping("/{id}")
	public Optional<ClasePractica> getClasePracticaById(@PathVariable int id) {
		return service.getClasePracticaById(id);
	}

	@PostMapping("/agregar")
	public ResponseEntity<String> agregar(@RequestParam int ID_instructor, @RequestParam int ID_matriculado,
			@RequestParam int ID_vehiculo, @RequestParam String Descripcion) {
		ClasePractica temp = new ClasePractica();
		temp.setIdInstructor(ID_instructor);
		temp.setIdVehiculo(ID_vehiculo);
		temp.setIdMatriculado(ID_matriculado);
		temp.setDescripcion(Descripcion);

		service.createClasePractica(temp);
		return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con Ã©xito: 201");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateClasePractica(@RequestParam int id, @RequestParam int ID_instructor,
			@RequestParam int ID_matriculado, @RequestParam int ID_vehiculo, @RequestParam String Descripcion) {
		ClasePractica clase = service.getClasePracticaById(id)
				.orElseThrow(() -> new RuntimeException("Clase practica not found"));
		clase.setIdClasePractica(id);
		clase.setIdInstructor(ID_instructor);
		clase.setIdMatriculado(ID_matriculado);
		clase.setIdVehiculo(ID_vehiculo);
		clase.setDescripcion(Descripcion);
		service.updateClasePractica(clase);
		return ResponseEntity.status(HttpStatus.CREATED).body("Dato actualizado con Ã©xito: 201");
	}

	@DeleteMapping("/{id}")
	public void deleteClasePractica(@PathVariable int id) {
		service.deleteClasePractica(id);
	}
}
