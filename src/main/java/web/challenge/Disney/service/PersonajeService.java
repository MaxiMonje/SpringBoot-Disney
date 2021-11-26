
package web.challenge.Disney.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.challenge.Disney.entity.PeliculaSerie;
import web.challenge.Disney.entity.Personaje;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.repository.PeliculaSerieRepository;
import web.challenge.Disney.repository.PersonajeRepository;

@Service
public class PersonajeService {
    
        @Autowired
        private PersonajeRepository personajeRepository;
        
        @Autowired
        private ImageService imageService;
        
        
    
        @Transactional
        public List<Personaje> listCharacters() throws ErrorService{
        
            List<Personaje> characters = new ArrayList();
            List<Personaje> charactersAlta = new ArrayList();

            characters= personajeRepository.findAll();

            for(Personaje p : characters){
                if(p.getAlta()==true){
                    charactersAlta.add(p);
                }
            }

            if(charactersAlta.isEmpty()){
                throw new ErrorService("No hay personajes disponibles");
            }else{
                return charactersAlta;
            }
        
        }
        
        @Transactional
        public void createCharacter(String name, MultipartFile image, Float weight, Integer age, String history, PeliculaSerie peliculaSerie) throws IOException{
            
           
            Personaje character = new Personaje();
            
            character.setAge(age);
            character.setHistory(history);
            character.setName(name);
            character.setPeliculaSerie(peliculaSerie);
            character.setWeight(weight);
            character.setImage(imageService.copiar(image));
            character.setAlta(true);

            personajeRepository.save(character);
        }
        
        @Transactional
        public void downCharacter(Integer id) throws ErrorService{
            
            
            
            Optional<Personaje> answer = personajeRepository.findById(id);
           
            if(answer.isPresent()){
                
                Personaje character = answer.get();
                character.setAlta(false);
            
                personajeRepository.save(character);
            }else{
                throw new ErrorService("No se encontró el personaje solicitado");
            }
        }
        
        @Transactional
        public void editCharacter(String name, MultipartFile image, Float weight, Integer age, String history, PeliculaSerie peliculaSerie, Integer id) throws ErrorService, IOException{
            
            Optional<Personaje> answer = personajeRepository.findById(id);
           
            if(answer.isPresent()){
                
                Personaje character = answer.get();
                character.setName(name);
                character.setHistory(history);
                character.setAge(age);
                character.setPeliculaSerie(peliculaSerie);
                character.setWeight(weight);
                character.setImage(imageService.copiar(image));
            
                personajeRepository.save(character);
            }else{
                throw new ErrorService("No se encontró el personaje solicitado");
            }
            
        }
        
        @Transactional
        public Personaje searchCharacter(String name) throws ErrorService{
            
            Optional<Personaje> answer = personajeRepository.findByName(name);
            
            if(answer.isPresent()){
                Personaje character = answer.get();
                
                return character;
                
            }else{
                throw new ErrorService("No se encontró el personaje solicitado");
            }
            
        }
        
        
        @Transactional
        public List<Personaje> filtrerAge(Integer age) throws ErrorService{
        
            List<Personaje> characters = new ArrayList();
            List<Personaje> charactersAge = new ArrayList();

            characters= personajeRepository.findAll();

            for(Personaje p : characters){
                if(p.getAge()==age && p.getAlta()==true){
                    charactersAge.add(p);
                }
            }

            if(charactersAge.isEmpty()){
                throw new ErrorService("No hay personajes con esa edad");
            }else{
                return charactersAge;
            }
        
        }
        
        @Transactional
        public List<Personaje> filtrerPeliculaSerie(Integer id) throws ErrorService{
        
            List<Personaje> characters = new ArrayList();
            List<Personaje> charactersMovies = new ArrayList();
            
            

            characters= personajeRepository.findAll();

            for(Personaje p : characters){
                if(p.getPeliculaSerie().getId()==id && p.getAlta()==true){
                    charactersMovies.add(p);
                }
            }

            if(charactersMovies.isEmpty()){
                throw new ErrorService("No hay personajes con esa pelicula o serie");
            }else{
                return charactersMovies;
            }
        
        }
        
        @Transactional
        public List<Personaje> filtrerWeight(Float weight) throws ErrorService{
        
            List<Personaje> characters = new ArrayList();
            List<Personaje> charactersWeight = new ArrayList();

            characters= personajeRepository.findAll();

            for(Personaje p : characters){
                if(p.getWeight()==weight && p.getAlta()==true){
                    charactersWeight.add(p);
                }
            }

            if(charactersWeight.isEmpty()){
                throw new ErrorService("No hay personajes con esa peso");
            }else{
                return charactersWeight;
            }
        
        }
        
}
