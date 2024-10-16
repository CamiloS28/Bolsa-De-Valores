package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    public Optional<Empresa> findById(Integer id);

    public List<Empresa> findAll();

    public Optional<Empresa> findByNombre(String nombre);

}
