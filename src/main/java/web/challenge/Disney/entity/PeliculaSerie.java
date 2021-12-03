
package web.challenge.Disney.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PeliculaSerie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String image;
    
    @Column(nullable=false)
    private String title;
    
    @Column(nullable=false)
    @CreatedDate
    private LocalDateTime date;
    
    
    @Column(nullable=false)
    private Integer qualification;
    
    @Column(nullable=false)
    private Boolean alta;
    
    @JoinColumn(nullable=false)
    @OneToMany
    private List<Personaje> personajes;
    
    @JoinColumn(nullable=false)
    @ManyToOne
    private Genero genero;
    
    
}
