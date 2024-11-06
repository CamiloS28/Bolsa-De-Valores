package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {


    @Query(value = "SELECT inversionista_id, empresa, cantidad, precio, monto_total FROM vista_transacciones_compras WHERE inversionista_id = :inversionistaId", nativeQuery = true)
    List<Object[]> findByInversionistaId(@Param("inversionistaId") Integer inversionistaId);

    @Query("SELECT t FROM Transaccion t JOIN t.inversionista i JOIN i.usuario u JOIN t.empresa e WHERE t.comisionista.id = :comisionista_id AND t.estado = false")
    List<Transaccion> findTransaccionesByComisionistaIdAndEstado(@Param("comisionista_id") Integer comisionistaId);

	@Query(value = "SELECT inversionista_id, nombre_empresa, cantidad, precio, monto_total FROM vista_transacciones_compras WHERE inversionista_id = :inversionistaId AND estado = :estado", nativeQuery = true)
	List<Object[]> findByInversionistaIdAndEstado(@Param("inversionistaId") Integer inversionistaId, @Param("estado") String estado);

	
	@Query(value = "SELECT transaccion_id,inversionista_id, nombre_empresa, cantidad, precio, monto_total FROM vista_transacciones_compras WHERE inversionista_id = :inversionistaId AND estado = :estado AND tipo = :tipo", nativeQuery = true)
	List<Object[]> findByInversionistaIdAndEstadoAndTipo(@Param("inversionistaId") Integer inversionistaId, @Param("estado") Boolean estado, @Param("tipo") String tipo);



}
