package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
