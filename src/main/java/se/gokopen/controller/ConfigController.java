package se.gokopen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String save(Config config,BindingResult errors, HttpServletRequest request, HttpServletResponse response){
        configService.saveConfig(config);
        
        return "startadmin";
    }

}
