package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Portafolio;
import co.edu.unbosque.repository.PortafolioRepository;

@Service
public class PortafolioService {
	@Autowired
	private PortafolioRepository repository;

	public List<Portafolio> getAll() {
		return repository.findAll();
	}

	public Optional<Portafolio> getPortafolioById(int id) {
		return repository.findById(id);
	}

	public Portafolio createPortafolio(Portafolio Portafolio) {
		return repository.save(Portafolio);
	}

	public Portafolio updatePortafolio(Portafolio PortafolioDetails) {

		return repository.save(PortafolioDetails);
	}

	public void deletePortafolio(int id) {
		repository.deleteById(id);
	}
}
