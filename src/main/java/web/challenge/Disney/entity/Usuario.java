
package web.challenge.Disney.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    
    @Id
    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String lastName;
    
    @Column(nullable=false)
    private String username;
    
    @Column(nullable=false)
    private String password;
    
    @Column(nullable=false)
    private Boolean alta;
    
   
}
