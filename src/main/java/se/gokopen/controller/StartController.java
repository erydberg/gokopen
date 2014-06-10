package se.gokopen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class StartController {

	@RequestMapping(method=RequestMethod.GET)
	public String start(HttpServletRequest request){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); 
		
	    request.setAttribute("username", name);
		return "start";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String startAdmin(HttpServletRequest request){
		
		return "startadmin";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
 
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
}
