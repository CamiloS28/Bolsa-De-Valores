package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Divisa;
import co.edu.unbosque.repository.DivisaRepository;

@Service
public class DivisaService {
	@Autowired
	private DivisaRepository repository;

	public List<Divisa> getAll() {
		return repository.findAll();
	}

	public Optional<Divisa> getDivisaById(int id) {
		return repository.findById(id);
	}

	public Divisa createDivisa(Divisa Divisa) {
		return repository.save(Divisa);
	}

	public Divisa updateDivisa(Divisa DivisaDetails) {

		return repository.save(DivisaDetails);
	}

	public void deleteDivisa(int id) {
		repository.deleteById(id);
	}
}
