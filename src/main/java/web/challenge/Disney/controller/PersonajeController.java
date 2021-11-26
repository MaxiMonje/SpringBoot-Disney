
package web.challenge.Disney.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import web.challenge.Disney.entity.PeliculaSerie;
import web.challenge.Disney.entity.Personaje;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.service.PersonajeService;

@Controller
public class PersonajeController {
    
    @Autowired
    private PersonajeService personajeService;
    
    
    @GetMapping("/characters")
    public ModelAndView showListCharacters() throws ErrorService{
        
        ModelAndView mav = new ModelAndView("characters");
        mav.addObject("listCharacters", personajeService.listCharacters());
        return mav;
    }
    
    @GetMapping("/characters")
    public ModelAndView filtrerAge(@RequestParam Integer age) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("characters");
        mav.addObject("listCharactersAge", personajeService.filtrerAge(age));
        return mav;
    }
    
    @GetMapping("/characters")
    public ModelAndView filtrerPeliculaSerie(@RequestParam Integer id) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("characters");
        mav.addObject("listCharactersPeliculaSerie", personajeService.filtrerPeliculaSerie(id));
        return mav;
    }
    
    @GetMapping("/characters")
    public ModelAndView filtrerWeight(@RequestParam Float weight) throws ErrorService{
        
        ModelAndView mav = new ModelAndView("characters");
        mav.addObject("listCharactersWeight", personajeService.filtrerWeight(weight));
        return mav;
    }
    
    @PostMapping("/characters/createCharacter")
    public String createCharacter(ModelMap modelo, @RequestParam String name, @RequestParam MultipartFile image, @RequestParam Float weight, @RequestParam Integer age, @RequestParam String history, @RequestParam PeliculaSerie peliculaSerie) throws ErrorService, IOException{
        
        personajeService.createCharacter(name, image, weight, age, history, peliculaSerie);
        
        return "redirect:/characters";
        
        
    }
    
    
    @PostMapping("/characters/editCharacter")
    public String editCharacter(ModelMap modelo, @RequestParam String name, @RequestParam MultipartFile image, @RequestParam Float weight, @RequestParam Integer age, @RequestParam String history, @RequestParam PeliculaSerie peliculaSerie, @RequestParam Integer id) throws ErrorService, IOException{
        
        personajeService.editCharacter(name, image, weight, age, history, peliculaSerie, id);
        
        return "redirect:/characters";
        
        
    }
    
    @PostMapping("/characters/downCharacter")
    public String downCharacter(ModelMap modelo,@RequestParam Integer id)throws ErrorService{
        
            personajeService.downCharacter(id);
       
        return "redirect:/characters";
    }
    
    @GetMapping("/characters/searchCharacter")
    public String searchCharacter(ModelMap modelo, @RequestParam String name) throws ErrorService{
        Personaje character = personajeService.searchCharacter(name);
        modelo.put("character", character);
        
        return "redirect:/searchCharacter";
    }
}
