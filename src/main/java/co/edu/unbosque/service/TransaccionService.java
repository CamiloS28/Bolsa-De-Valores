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

	public List<Object[]> getTransaccionesPorInversionistaIdYEstado(Integer inversionistaId, String estado) {
		return repository.findByInversionistaIdAndEstado(inversionistaId, estado);
	}

	public List<Object[]> getTransaccionesPorInversionistaIdEstadoYTipo(Integer inversionistaId, Boolean estado,
			String tipo) {
		return repository.findByInversionistaIdAndEstadoAndTipo(inversionistaId, estado, tipo);
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

	public List<Transaccion> findTransaccionesByComisionistaIdAndEstado(Integer comisionistaId) {
		return repository.findTransaccionesByComisionistaIdAndEstado(comisionistaId);
	}

	public Transaccion aceptarTransaccion(Integer transaccionId) {
		Transaccion transaccion = repository.findById(transaccionId).get();
		transaccion.setEstado(true);
		return repository.save(transaccion);
	}

	public Optional<Integer> findCantidad(Integer transaccionId) {
		return repository.findCantidadByTransaccionId(transaccionId);
	}

	public List<Transaccion> findVentasDeInversionistas(Integer comisionistaId, String tipo) {
		return repository.findVentasDeInversionistas(comisionistaId, tipo);
	}
}
