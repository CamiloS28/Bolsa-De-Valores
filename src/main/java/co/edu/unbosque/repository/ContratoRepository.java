package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    public Optional<Contrato> findById(Integer contrato_id);

    public List<Contrato> findAll();

}
