package se.gokopen.controller;


import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.model.Config;
import se.gokopen.service.ConfigService;

@RequestMapping("/admin/config")
@Controller
public class ConfigController {
    
    @Autowired
    private ConfigService configService;
    
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView showConfig(){
        Config config = configService.getCurrentConfig();
        ModelMap map = new ModelMap();
        map.put("config", config);
        return new ModelAndView("config",map);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String save(@Valid Config config, BindingResult errors, ModelMap model) throws ParseException{

        if(errors.hasErrors()) {
            model.addAttribute("errormsg","Det är något som inte är ifyllt korrekt. Kolla datumet.");
            return "config";
        }
        model.addAttribute("confirmmsg", "Konfigurationen är sparad");
        configService.saveConfig(config);
        
        return "startadmin";
    }

}
