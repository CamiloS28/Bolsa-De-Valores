package co.edu.unbosque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.ClasePractica;

public interface RepositoryClasePractica extends JpaRepository<ClasePractica, Integer> {
//	@Procedure("eliminar_cauce")
//	void eliminarRegistroCauces(@Param("p_cauce") String nombreCauce);
//
//	@Query("SELECT c.cauce FROM Cauces c")
//	List<String> nombreCauces();
}
