package se.gokopen.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import se.gokopen.dao.UserNotFoundException;
import se.gokopen.model.User;
import se.gokopen.service.UserService;

@RequestMapping("/admin/user")
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute("userroles")
    public Map<String,String> populateUserRoles(){
        Map<String,String> userroles = new LinkedHashMap<String,String>();
        userroles.put("ROLE_USER", "Kontrollant");
        userroles.put("ROLE_STARTFINISH", "Start-mål-admin");
        userroles.put("ROLE_ADMIN", "Administratör");
        return userroles;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView listUser(HttpServletRequest request){
        List<User> users = userService.getAllUsers();
        ModelMap map = new ModelMap();
        map.put("users", users);
        return new ModelAndView("listusers",map);
    }
    
    @RequestMapping(value="/new", method=RequestMethod.GET)
    public ModelAndView createUser(){
        User user = new User();
        ModelMap map = new ModelMap();
        map.addAttribute("user",user);
        return new ModelAndView("edituser",map);
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveUser(@ModelAttribute("user")User user, HttpServletRequest request){
        userService.saveUser(user);       
        return "redirect:/admin/user";
    }
    
    @RequestMapping(value="/open/{id}", method=RequestMethod.GET)
    public ModelAndView openUser(@PathVariable String id){
        User user = null;
        try {
            user = userService.getUserById(Integer.parseInt(id));
        } catch (UserNotFoundException e) {

            e.printStackTrace();
        }
        ModelMap map = new ModelMap();
        map.addAttribute("user",user);
        return new ModelAndView("edituser",map);
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteUser(@PathVariable String id){
        try {
            userService.deleteUserById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "redirect:/admin/user";
    }
}
