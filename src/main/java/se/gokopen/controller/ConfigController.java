package se.gokopen.controller;


import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.model.Config;
import se.gokopen.service.ConfigService;

@RequestMapping("/admin/config")
@Controller
public class ConfigController {

    public static final String CONFIG = "config";
    @Autowired
    private ConfigService configService;

    @GetMapping
    public ModelAndView showConfig(){
        Config config = configService.getCurrentConfig();
        ModelMap map = new ModelMap();
        map.put(CONFIG, config);
        return new ModelAndView(CONFIG,map);
    }
    
    @PostMapping
    public String save(@Valid Config config, BindingResult errors, ModelMap model) throws ParseException{

        if(errors.hasErrors()) {
            model.addAttribute("errormsg","Det är något som inte är ifyllt korrekt. Kolla datumet.");
            return CONFIG;
        }
        model.addAttribute("confirmmsg", "Konfigurationen är sparad");
        configService.saveConfig(config);
        
        return "startadmin";
    }

}
