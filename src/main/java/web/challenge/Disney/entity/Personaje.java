
package web.challenge.Disney.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Personaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String image;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private Float weight;
    
    @Column(nullable=false)
    private Integer age;
    
    @Column(nullable=false)
    private String history;
    
    @Column(nullable=false)
    private Boolean alta;
    
    @JoinColumn(nullable=false)
    @ManyToOne
    private PeliculaSerie peliculaSerie;
}
