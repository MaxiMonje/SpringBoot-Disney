
package web.challenge.Disney.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import web.challenge.Disney.entity.Genero;
import web.challenge.Disney.entity.PeliculaSerie;
import web.challenge.Disney.entity.Personaje;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.service.PeliculaSerieService;

@Controller
@RequestMapping("/movies")
public class PeliculaSerieController {
    
    @Autowired
    private PeliculaSerieService peliculaSerieService;
    
    @GetMapping("/")
    public ModelAndView showListCharacters() throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        mav.addObject("listMovies", peliculaSerieService.listPeliculaSerie());
        return mav;
    }
    
    @GetMapping("/filtrerTitle")
    public ModelAndView filtrerTitle(@RequestParam String title) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        mav.addObject("listMoviesTitle", peliculaSerieService.searchMovie(title));
        return mav;
    }
    
    @GetMapping("/filtrerGenero")
    public ModelAndView filtrerGenero(@RequestParam Integer id) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        mav.addObject("listMoviesGenero", peliculaSerieService.filtrerGenero(id));
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createMovie(){
        ModelAndView mav= new ModelAndView("movie-form");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView save(RedirectAttributes attributes ,@RequestParam String title, @RequestParam MultipartFile image, @RequestParam LocalDateTime date, @RequestParam Integer qualification, @RequestParam List<Personaje> characters, @RequestParam Genero genero){

        try {
            peliculaSerieService.createMovie(title,image,date,qualification, characters,genero);

        }catch (Exception e){
            attributes.addFlashAttribute("errorCreate", e.getMessage());
            attributes.addFlashAttribute("title", title);
            attributes.addFlashAttribute("image", image);
            attributes.addFlashAttribute("date", date);
            attributes.addFlashAttribute("qualification", qualification);
            attributes.addFlashAttribute("characters", characters);
            attributes.addFlashAttribute("genero", genero);

            return new RedirectView("/create");
        }

        return new RedirectView("/movies");
    }
    
    @PostMapping("/editMovie")
    public String editMovie(ModelMap modelo, @RequestParam String title,  @RequestParam MultipartFile image, @RequestParam LocalDateTime date, @RequestParam Integer qualification, @RequestParam List<Personaje> characters, @RequestParam Integer id, @RequestParam Genero genero) throws ErrorService, IOException{
        
        peliculaSerieService.editMovie(title, image, date, qualification, characters, id, genero);
        
        return "redirect:/movies";
        
        
    }
    
    @PostMapping("/downMovie")
    public String downCharacter(ModelMap modelo,@RequestParam Integer id)throws ErrorService{
        
            peliculaSerieService.downMovie(id);
       
        return "redirect:/movies";
    }
    
    @GetMapping("/searchMovie")
    public String searchCharacter(ModelMap modelo, @RequestParam String title) throws ErrorService{
        PeliculaSerie movie = peliculaSerieService.searchMovie(title);
        modelo.put("movie", movie);
        
        return "redirect:/searchMovie";
    }
    
    @GetMapping("/filtrerAScDESC")
    public ModelAndView filtrerAScDESC(String order) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("movies");
        
        if(order.equals("ASC")){
            
            mav.addObject("listMoviesASC", peliculaSerieService.ordenarASC());
            
        }else if(order.equals("DESC")){
            
            mav.addObject("listMoviesDESC", peliculaSerieService.ordenarDESC());
        }
        
       return mav;
    }
    
    
}
