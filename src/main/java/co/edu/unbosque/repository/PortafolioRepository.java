package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.model.Portafolio;

public interface PortafolioRepository extends JpaRepository<Portafolio, Integer> {

}
