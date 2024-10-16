package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.model.Comisionista;

import java.util.List;
import java.util.Optional;

public interface ComisionistaRepository extends CrudRepository<Comisionista, Integer> {
	public Optional<Comisionista> findById(Integer id);

	public List<Comisionista> findAll();

}
