package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import co.edu.unbosque.model.Billetera;
import java.util.List;

public interface BilleteraRepository extends JpaRepository<Billetera, Integer> {

    @Query("SELECT b FROM Billetera b WHERE b.usuario.id = :usuarioId")
    List<Billetera> findBilleteraByUsuarioId(@Param("usuarioId") Integer usuarioId);

}
