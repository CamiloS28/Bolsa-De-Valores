package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Accion;
import co.edu.unbosque.repository.AccionRepository;

@Service
public class InversionistaService {

	
	@Autowired
	private AccionRepository repository;

	public List<Accion> getAll() {
		return repository.findAll();
	}

	public Optional<Accion> getAccionById(int id) {
		return repository.findById(id);
	}

	public Accion createAccion(Accion Accion) {
		return repository.save(Accion);
	}

	public Accion updateAccion(Accion AccionDetails) {

		return repository.save(AccionDetails);
	}

	public void deleteAccion(int id) {
		repository.deleteById(id);
	}

}
