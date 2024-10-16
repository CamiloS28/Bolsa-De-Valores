package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.model.Alerta_Inversionista;

public interface Alerta_InversionistaRepository extends JpaRepository<Alerta_Inversionista, Integer> {

    public Optional<Alerta_Inversionista> findById(Integer id);

    public List<Alerta_Inversionista> findAll();

}
