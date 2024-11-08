package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.repository.log_usuarioRepository;
import co.edu.unbosque.model.log_usuario;
import java.util.List;
import java.util.Optional;

@Service
public class log_usuarioService {

    @Autowired
    private log_usuarioRepository repository;

    public List<log_usuario> getAll() {
        return repository.findAll();
    }

    public Optional<log_usuario> getlog_usuarioById(int id) {
        return repository.findById(id);
    }

    public log_usuario createlog_usuario(log_usuario log_usuario) {
        return repository.save(log_usuario);
    }

    public log_usuario updatelog_usuario(log_usuario log_usuarioDetails) {

        return repository.save(log_usuarioDetails);
    }

    public void deletelog_usuario(int id) {
        repository.deleteById(id);
    }

}
