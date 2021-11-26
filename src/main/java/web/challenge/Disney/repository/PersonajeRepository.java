
package web.challenge.Disney.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import web.challenge.Disney.entity.Personaje;


public interface PersonajeRepository extends JpaRepository<Personaje, Integer>{
    
    Optional<Personaje> findByName(String name);
    
    boolean existsUsuarioByName(String name);
}
