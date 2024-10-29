package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Comisionista;
import co.edu.unbosque.repository.ComisionistaRepository;

@Service
public class ComisionistaService {

	@Autowired
	private ComisionistaRepository repository;

	public List<Comisionista> getAll() {
		return repository.findAll();
	}

	public Optional<Comisionista> getComisionistaById(int id) {
		return repository.findById(id);
	}

	public Comisionista createComisionista(Comisionista Comisionista) {
		return repository.save(Comisionista);
	}

	public Comisionista updateComisionista(Comisionista ComisionistaDetails) {

		return repository.save(ComisionistaDetails);
	}

	public void deleteComisionista(int id) {
		repository.deleteById(id);
	}

	public boolean existsById(int id) {

		return repository.existsById(id);
	}
}
