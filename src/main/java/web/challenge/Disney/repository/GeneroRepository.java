
package web.challenge.Disney.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import web.challenge.Disney.entity.Genero;


public interface GeneroRepository extends JpaRepository<Genero, Integer>{
    
    Optional<Genero> findByName(String name);
    
    boolean existsGeneroByName(String name);
}
