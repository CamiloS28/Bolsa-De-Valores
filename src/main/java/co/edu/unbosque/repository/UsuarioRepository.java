package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	public Optional<Usuario> findById(Integer id);

	public List<Usuario> findAll();

}
