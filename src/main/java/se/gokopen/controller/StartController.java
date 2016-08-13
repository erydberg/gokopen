package se.gokopen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.gokopen.model.Config;
import se.gokopen.service.ConfigService;

@RequestMapping("/")
@Controller
public class StartController {
    
    @Autowired
    private ConfigService configService;

	@RequestMapping(method=RequestMethod.GET)
	public String start(HttpServletRequest request){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); 

	    Config config = configService.getCurrentConfig();
	    request.setAttribute("config", config);
	    request.setAttribute("username", name);
		return "start";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String startAdmin(HttpServletRequest request){
	    Config config = configService.getCurrentConfig();
        request.setAttribute("config", config);
		return "startadmin";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
	    Config config = configService.getCurrentConfig();
        request.setAttribute("config", config);
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
