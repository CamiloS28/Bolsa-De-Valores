package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.model.Inversionista;

public interface InversionistaRepository extends JpaRepository<Inversionista, Integer> {

    public Optional<Inversionista> findById(Integer id);

    public List<Inversionista> findAll();
}
