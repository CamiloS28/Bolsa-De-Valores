package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Comisionista;
import co.edu.unbosque.model.Contrato;
import co.edu.unbosque.model.Inversionista;
import co.edu.unbosque.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository repository;

	public List<Contrato> getAll() {
		return repository.findAll();
	}

	public Optional<Contrato> getContratoById(int id) {
		return repository.findById(id);
	}

	public Contrato createContrato(Contrato Contrato) {
		return repository.save(Contrato);
	}

	public Contrato updateContrato(Contrato ContratoDetails) {

		return repository.save(ContratoDetails);
	}

	public void deleteContrato(int id) {
		repository.deleteById(id);
	}

	public List<Inversionista> obtenerInversionistasPorComisionista(Integer comisionistaId) {
		return repository.findInversionistasByComisionistaId(comisionistaId);
	}

	public Contrato cancelarContrato(int id) {
		Contrato contrato = repository.findById(id).get();
		contrato.setEstado(false);
		return repository.save(contrato);
	}

	public List<Object[]> obtenerComisionistaYContrato(Integer inversionistaId) {
		return repository.findComisionistaIdAndContratoIdByInversionistaId(inversionistaId);
	}
}
