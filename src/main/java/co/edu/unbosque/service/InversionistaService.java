package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Inversionista;
import co.edu.unbosque.repository.InversionistaRepository;

@Service
public class InversionistaService {

	
	@Autowired
	private InversionistaRepository repository;

	public List<Inversionista> getAll() {
		return repository.findAll();
	}

	public Optional<Inversionista> getInversionistaById(int id) {
		return repository.findById(id);
	}

	public Inversionista createInversionista(Inversionista Inversionista) {
		return repository.save(Inversionista);
	}

	public Inversionista updateInversionista(Inversionista InversionistaDetails) {

		return repository.save(InversionistaDetails);
	}

	public void deleteInversionista(int id) {
		repository.deleteById(id);
	}

}
