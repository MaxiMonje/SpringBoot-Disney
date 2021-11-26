
package web.challenge.Disney.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import web.challenge.Disney.entity.Usuario;


public interface UserRepository extends JpaRepository<Usuario, String>{
    Optional<Usuario> findByUsername(String username);
    
    boolean existsUsuarioByUsername(String username);
}
