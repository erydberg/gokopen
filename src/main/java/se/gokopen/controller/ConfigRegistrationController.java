package se.gokopen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import se.gokopen.model.ConfigRegistration;
import se.gokopen.service.ConfigRegistrationService;

import javax.validation.Valid;

@RequestMapping("/admin/configregistration")
@Controller
public class ConfigRegistrationController {


    @Autowired
    private ConfigRegistrationService configRegistrationService;

    @GetMapping
    public ModelAndView showRegistrationConfig(){
        ConfigRegistration configRegistration = configRegistrationService.getCurrentConfig();
        ModelMap map = new ModelMap();
        map.put("configregistration",configRegistration);
        return new ModelAndView("configregistration", map);
    }

    @PostMapping
    public String save(@Valid ConfigRegistration configRegistration, BindingResult errors, ModelMap model){
        if(errors.hasErrors()){
            model.addAttribute("errormsg", "Det är något du inte fyllt i rätt.");
            return "configregistration";
        }
        configRegistrationService.saveConfigRegistration(configRegistration);
        model.addAttribute("confirmmsg", "Konfigurationen är sparad");

        return "startadmin";
    }

}
