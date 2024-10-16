package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.model.Accion;

public interface AccionRepository extends JpaRepository<Accion, Integer> {

    public Optional<Accion> findById(Integer id);

    public List<Accion> findAll();

}
