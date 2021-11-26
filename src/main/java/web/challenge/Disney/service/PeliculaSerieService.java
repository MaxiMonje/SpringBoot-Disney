
package web.challenge.Disney.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.challenge.Disney.entity.Genero;
import web.challenge.Disney.entity.PeliculaSerie;
import web.challenge.Disney.entity.Personaje;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.repository.GeneroRepository;
import web.challenge.Disney.repository.PeliculaSerieRepository;

@Service
public class PeliculaSerieService {
    
    @Autowired
    private PeliculaSerieRepository peliculaSerieRepository;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private GeneroRepository generoRepository;
    
    @Transactional
    public List<PeliculaSerie> listPeliculaSerie() throws ErrorService{
        
            List<PeliculaSerie> movies = new ArrayList();
            List<PeliculaSerie> moviesAlta = new ArrayList();

            movies= peliculaSerieRepository.findAll();

            for(PeliculaSerie p : movies){
                if(p.getAlta()==true){
                    moviesAlta.add(p);
                }
            }

            if(moviesAlta.isEmpty()){
                throw new ErrorService("No hay Peliculas o Series disponibles");
            }else{
                return moviesAlta;
            }
        
        }
    
    
        @Transactional
        public void createMovie(String title, MultipartFile image, LocalDateTime date, Integer qualification, List<Personaje> characters, Genero genero) throws IOException{
            
           
            PeliculaSerie movie = new PeliculaSerie();
            
            movie.setTitle(title);
            movie.setQualification(qualification);
            movie.setDate(date);
            movie.setImage(imageService.copiar(image));
            movie.setAlta(true);
            movie.setGenero(genero);
            movie.setPersonajes(characters);

            peliculaSerieRepository.save(movie);
        }
        
        @Transactional
        public void downMovie(Integer id) throws ErrorService{
            
            Optional<PeliculaSerie> answer = peliculaSerieRepository.findById(id);
           
            if(answer.isPresent()){
                
                PeliculaSerie movie = answer.get();
                movie.setAlta(false);
            
                peliculaSerieRepository.save(movie);
            }else{
                throw new ErrorService("No se encontró la pelicula o serie solicitada");
            }
        }
        
        @Transactional
        public void editMovie(String title, MultipartFile image, LocalDateTime date, Integer qualification, List<Personaje> characters, Integer id, Genero genero) throws ErrorService, IOException{
            
            Optional<PeliculaSerie> answer = peliculaSerieRepository.findById(id);
           
            if(answer.isPresent()){
                
                PeliculaSerie movie = answer.get();
                movie.setDate(date);
                movie.setPersonajes(characters);
                movie.setQualification(qualification);
                movie.setTitle(title);
                movie.setGenero(genero);
                movie.setImage(imageService.copiar(image));
            
                peliculaSerieRepository.save(movie);
            }else{
                throw new ErrorService("No se encontró la pelicula o serie solicitada");
            }
            
        }
        
        @Transactional
        public PeliculaSerie searchMovie(String title) throws ErrorService{
            
            Optional<PeliculaSerie> answer = peliculaSerieRepository.findByTitle(title);
            
            if(answer.isPresent()){
                PeliculaSerie movie = answer.get();
                
                return movie;
                
            }else{
                throw new ErrorService("No se encontró la pelicula o serie solicitada");
            }
            
        }
        
        @Transactional
        public List<PeliculaSerie> filtrerGenero(Integer id) throws ErrorService{
        
            List<PeliculaSerie> movies = new ArrayList();
            List<PeliculaSerie> moviesGenero = new ArrayList();
            
            

            movies= peliculaSerieRepository.findAll();

            for(PeliculaSerie p : movies){
                if(p.getGenero().getId()==id && p.getAlta()==true){
                    moviesGenero.add(p);
                }
            }

            if(moviesGenero.isEmpty()){
                throw new ErrorService("No hay peliculas o Series con género");
            }else{
                return  moviesGenero;
            }
        
        }
        
        @Transactional
        public List<PeliculaSerie> ordenarASC() throws ErrorService{
            
            List<PeliculaSerie> movies = new ArrayList();
            List<PeliculaSerie> moviesASC = new ArrayList();
            
            movies=peliculaSerieRepository.findAll();
            
            
            for(PeliculaSerie p : movies){
                if(p.getAlta()==true){
                    
                }
            }
            
            if(moviesASC.isEmpty()){
                throw new ErrorService("No hay peliculas o Series disponibles");
            }else{
                return  moviesASC;
            }
            
            
        }
        
        @Transactional
        public List<PeliculaSerie> ordenarDESC() throws ErrorService{
            
            List<PeliculaSerie> movies = new ArrayList();
            List<PeliculaSerie> moviesDESC = new ArrayList();
            
            movies=peliculaSerieRepository.findAll();
            
            for(PeliculaSerie p : movies){
                if(p.getAlta()==true){
                    
                }
            }
            
            if(moviesDESC.isEmpty()){
                throw new ErrorService("No hay peliculas o Series disponibles");
            }else{
                return  moviesDESC;
            }
        }
}
