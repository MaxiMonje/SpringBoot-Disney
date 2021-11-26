
package web.challenge.Disney.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.challenge.Disney.entity.Genero;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.repository.GeneroRepository;

@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;
    
    @Transactional
    public List<Genero> listGeneros() throws ErrorService{

        List<Genero> generos = new ArrayList();
        List<Genero> generosAlta = new ArrayList();

        generos= generoRepository.findAll();

        for(Genero g : generos){
            if(g.getAlta()==true){
                generosAlta.add(g);
            }
        }

        if(generosAlta.isEmpty()){
            throw new ErrorService("No hay generos disponibles");
        }else{
            return generosAlta;
        }

    }
}
