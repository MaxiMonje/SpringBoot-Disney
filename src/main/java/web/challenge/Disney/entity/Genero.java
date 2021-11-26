
package web.challenge.Disney.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    @OneToMany(mappedBy="genero")
    private List<PeliculaSerie> peliculasSeries;
}
