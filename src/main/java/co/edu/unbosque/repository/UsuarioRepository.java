package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.model.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuarios, Integer> {
	public Optional<Usuarios> findById(Integer id);

	public List<Usuarios> findAll();

}
