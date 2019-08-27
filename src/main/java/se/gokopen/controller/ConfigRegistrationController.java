package se.gokopen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.gokopen.model.ConfigRegistration;
import se.gokopen.service.ConfigRegistrationService;

@RequestMapping("/admin/configregistration")
@Controller
public class ConfigRegistrationController {


    @Autowired
    private ConfigRegistrationService configRegistrationService;

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView showRegistrationConfig(){
        ConfigRegistration configRegistration = configRegistrationService.getCurrentConfig();
        ModelMap map = new ModelMap();
        map.put("configregistration",configRegistration);
        return new ModelAndView()
    }


}
