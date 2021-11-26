
package web.challenge.Disney.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import web.challenge.Disney.entity.PeliculaSerie;


public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
    
    Optional<PeliculaSerie> findByTitle(String title);
    
    boolean existsMovieByTitle(String title);
}
