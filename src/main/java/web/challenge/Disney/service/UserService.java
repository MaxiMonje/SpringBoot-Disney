
package web.challenge.Disney.service;

import java.util.Collections;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import web.challenge.Disney.entity.Usuario;
import web.challenge.Disney.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private MailService emailService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String MESSAGE = "El username ingresado no existe %s";
    
    @Transactional
    public void createUser(String name, String lastname, String password, String username, String email) throws Exception{
        
        Usuario user = new Usuario();

        user.setName(name);
        user.setLastName(lastname);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setUsername(username);
        user.setAlta(true);
        
        userRepository.save(user);
        
        emailService.send(email);
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE, username)));
        
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        
        session.setAttribute("name", user.getName());
        session.setAttribute("lastName", user.getLastName());
        
        
        return new User(user.getUsername(), user.getPassword(), Collections.singletonList(null));

    }
}
