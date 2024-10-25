package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository repository;

	public List<Usuario> getAll() {
		return repository.findAll();
	}

	public Optional<Usuario> getUsuarioById(int id) {
		return repository.findById(id);
	}

	public Usuario createUsuario(Usuario Usuario) {
		return repository.save(Usuario);
	}

	public Usuario updateUsuario(Usuario UsuarioDetails) {

		return repository.save(UsuarioDetails);
	}

	public void deleteUsuario(int id) {
		repository.deleteById(id);
	}
}
