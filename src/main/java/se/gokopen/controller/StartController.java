package se.gokopen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.gokopen.model.Config;
import se.gokopen.service.ConfigService;
import se.gokopen.service.PatrolService;

@RequestMapping("/")
@Controller
public class StartController {
    
    @Autowired
    private ConfigService configService;
    
    @Autowired
    private PatrolService patrolService;
    
    @ModelAttribute("config")
    public Config loadConfig() {
        return configService.getCurrentConfig();
    }
    

	@RequestMapping(method=RequestMethod.GET)
	public String start(ModelMap map){
	    map.addAttribute("registrationOpen",RegistrationChecker.isOpenForRegistration(loadConfig(), patrolService.getAllPatrols().size()));
		return "welcomepage";
	}
	
	@GetMapping("/startmenu")
	public String startMenu() {
	    
	    return "start";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String startAdmin(HttpServletRequest request){

		return "startadmin";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {

		return "login";
 
	}
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(HttpServletRequest request) {
 
		request.setAttribute("error", "Felaktigt användarnamn eller lösenord");
		return "login";
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout() {
 
		return "login";
 
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String accessDenied(HttpServletRequest request){
	    return "accessdenied";
	}
}
