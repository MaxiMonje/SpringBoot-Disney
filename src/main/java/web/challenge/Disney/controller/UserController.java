
package web.challenge.Disney.controller;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import web.challenge.Disney.error.ErrorService;
import web.challenge.Disney.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/createUser")
    public ModelAndView createUsuario(Principal principal){
        
        ModelAndView modelAndView = new ModelAndView("/createUser");
       
        
        if(principal != null){
            modelAndView.setViewName("redirect:/");
        }
        
       return modelAndView;
    }
    
    @PostMapping("/createUser/save")
    public String guardar(ModelMap modelo, @RequestParam String name, @RequestParam String lastname,@RequestParam String email, @RequestParam String username, @RequestParam String password) throws Exception{
       try{
           userService.createUser(name, lastname, password, username, email);
         
       } catch (ErrorService ex) {
            modelo.put("errorRegistrar", ex.getMessage());
            
            return "createUser.html";
        }
        return "redirect:/";
    }
}
