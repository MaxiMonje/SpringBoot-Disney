
package web.challenge.Disney.entity;

import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Genero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String image;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private Boolean alta;
    
    @JoinColumn(nullable=false)
    @OneToMany
    private List<PeliculaSerie> peliculasSeries;
}
