package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    public Optional<Transaccion> findById(Integer id);

    public List<Transaccion> findAll();

}
