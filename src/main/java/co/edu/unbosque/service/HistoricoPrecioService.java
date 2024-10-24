package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.HistoricoPrecio;
import co.edu.unbosque.repository.HistoricoPrecioRepository;

@Service
public class HistoricoPrecioService {
	@Autowired
	private HistoricoPrecioRepository repository;

	public List<HistoricoPrecio> getAll() {
		return repository.findAll();
	}

	public Optional<HistoricoPrecio> getHistoricoPrecioById(int id) {
		return repository.findById(id);
	}

	public HistoricoPrecio createHistoricoPrecio(HistoricoPrecio HistoricoPrecio) {
		return repository.save(HistoricoPrecio);
	}

	public HistoricoPrecio updateHistoricoPrecio(HistoricoPrecio HistoricoPrecioDetails) {

		return repository.save(HistoricoPrecioDetails);
	}

	public void deleteHistoricoPrecio(int id) {
		repository.deleteById(id);
	}


}
