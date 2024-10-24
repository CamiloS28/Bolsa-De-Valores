package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Alerta_Inversionista;
import co.edu.unbosque.repository.Alerta_InversionistaRepository;

@Service
public class Alerta_InversionistaService {
	@Autowired
	private Alerta_InversionistaRepository repository;

	public List<Alerta_Inversionista> getAll() {
		return repository.findAll();
	}

	public Optional<Alerta_Inversionista> getAlerta_InversionistaById(int id) {
		return repository.findById(id);
	}

	public Alerta_Inversionista createAlerta_Inversionista(Alerta_Inversionista Alerta_Inversionista) {
		return repository.save(Alerta_Inversionista);
	}

	public Alerta_Inversionista updateAlerta_Inversionista(Alerta_Inversionista Alerta_InversionistaDetails) {

		return repository.save(Alerta_InversionistaDetails);
	}

	public void deleteAlerta_Inversionista(int id) {
		repository.deleteById(id);
	}

	public boolean existsById(int id) {

		return repository.existsById(id);
	}

}