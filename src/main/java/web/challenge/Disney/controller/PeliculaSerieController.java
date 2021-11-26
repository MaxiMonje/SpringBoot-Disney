
package web.challenge.Disney.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import web.challenge.Disney.entity.Genero;
import web.challenge.Disney.entity.PeliculaSerie;
import web.challenge.Disney.entity.Personaje;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.service.PeliculaSerieService;

@Controller
public class PeliculaSerieController {
    
    @Autowired
    private PeliculaSerieService peliculaSerieService;
    
    @GetMapping("/movies")
    public ModelAndView showListCharacters() throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        mav.addObject("listMovies", peliculaSerieService.listPeliculaSerie());
        return mav;
    }
    
    @GetMapping("/movies")
    public ModelAndView filtrerTitle(@RequestParam String title) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        mav.addObject("listMoviesTitle", peliculaSerieService.searchMovie(title));
        return mav;
    }
    
    @GetMapping("/movies")
    public ModelAndView filtrerGenero(@RequestParam Integer id) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        mav.addObject("listMoviesGenero", peliculaSerieService.filtrerGenero(id));
        return mav;
    }
    
    @PostMapping("/movies/createMovie")
    public String createMovie(ModelMap modelo, @RequestParam String title, @RequestParam MultipartFile image, @RequestParam LocalDateTime date, @RequestParam Integer qualification, @RequestParam List<Personaje> characters, @RequestParam Genero genero) throws ErrorService, IOException{
        
        peliculaSerieService.createMovie(title, image, date, qualification, characters, genero);
        
        return "redirect:/movies";
        
        
    }
    
    
    @PostMapping("/characters/editMovie")
    public String editMovie(ModelMap modelo, @RequestParam String title,  @RequestParam MultipartFile image, @RequestParam LocalDateTime date, @RequestParam Integer qualification, @RequestParam List<Personaje> characters, @RequestParam Integer id, @RequestParam Genero genero) throws ErrorService, IOException{
        
        peliculaSerieService.editMovie(title, image, date, qualification, characters, id, genero);
        
        return "redirect:/movies";
        
        
    }
    
    @PostMapping("/movies/downMovie")
    public String downCharacter(ModelMap modelo,@RequestParam Integer id)throws ErrorService{
        
            peliculaSerieService.downMovie(id);
       
        return "redirect:/movies";
    }
    
    @GetMapping("/movies/searchMovie")
    public String searchCharacter(ModelMap modelo, @RequestParam String title) throws ErrorService{
        PeliculaSerie movie = peliculaSerieService.searchMovie(title);
        modelo.put("movie", movie);
        
        return "redirect:/searchMovie";
    }
    
    @GetMapping("/movies")
    public ModelAndView filtrerASC(String order) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        
        if(order.equals("ASC")){
            
            mav.addObject("listMoviesASC", peliculaSerieService.ordenarASC());
            
        }else if(order.equals("DESC")){
            
            mav.addObject("listMoviesDESC", peliculaSerieService.ordenarDESC());
        }
        
       return mav;
    }
    
    
}
