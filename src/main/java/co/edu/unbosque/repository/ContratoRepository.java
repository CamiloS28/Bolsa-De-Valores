package co.edu.unbosque.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import co.edu.unbosque.model.Contrato;
import co.edu.unbosque.model.Inversionista;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    @Query("SELECT c.inversionista FROM Contrato c WHERE c.comisionista.id = :comisionista_id")
    List<Inversionista> findInversionistasByComisionistaId(@Param("comisionista_id") int comisionistaId);
}
