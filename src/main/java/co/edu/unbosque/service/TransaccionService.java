package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Transaccion;
import co.edu.unbosque.repository.TransaccionRepository;

@Service
public class TransaccionService {

	@Autowired
	private TransaccionRepository repository;

	public List<Transaccion> getAll() {
		return repository.findAll();
	}

	public Optional<Transaccion> getTransaccionById(int id) {
		return repository.findById(id);
	}

    public List<Object[]> getTransaccionesPorInversionistaId(Integer inversionistaId) {
        return repository.findByInversionistaId(inversionistaId);
    }
	public Transaccion createTransaccion(Transaccion Transaccion) {
		return repository.save(Transaccion);
	}

	public Transaccion updateTransaccion(Transaccion TransaccionDetails) {

		return repository.save(TransaccionDetails);
	}

	public void deleteTransaccion(int id) {
		repository.deleteById(id);
	}
}
