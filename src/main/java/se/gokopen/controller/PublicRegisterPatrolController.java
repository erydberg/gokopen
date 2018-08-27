package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Config;
import se.gokopen.model.Patrol;
import se.gokopen.model.Status;
import se.gokopen.model.Track;
import se.gokopen.service.ConfigService;
import se.gokopen.service.PatrolService;
import se.gokopen.service.TrackService;

@Controller
@RequestMapping("/register")
public class PublicRegisterPatrolController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private PatrolService patrolService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Track.class, new TrackEditor(this.trackService));
    }
    
    @ModelAttribute("tracks")
    public List<Track> populateTracks() {
        return trackService.getAllTracks();
    }
    
    @ModelAttribute("config")
    public Config loadConfiguration() {
        return configService.getCurrentConfig();
    }

    @GetMapping
    public ModelAndView showRegisterPatrolForm(HttpServletRequest request) {
        int noOfPatrols = patrolService.getAllPatrols().size();
        if(RegistrationChecker.isOpenForRegistration(loadConfiguration(), noOfPatrols)) {
            ModelMap map = new ModelMap();
            map.put("patrol", new Patrol());
            map.put("registeredpatrols", noOfPatrols);
            return new ModelAndView("publicregisterpatrol",map);    
        }else {
            return new ModelAndView("publicregistrationnotopen");
        }
    }

    
    @PostMapping
    public String savePatrol(@Valid Patrol patrol, BindingResult bindingResult, ModelMap model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errormsg","Ops, du missade visst att fylla i viktig information.");
            int noOfPatrols = patrolService.getAllPatrols().size();
            model.addAttribute("registeredpatrols", noOfPatrols);
            return "publicregisterpatrol";
        }
        try {
            patrol.setStatus(Status.REGISTERED);
            patrolService.savePatrol(patrol);
            return "publicregisterconfirmation";
        } catch (PatrolNotSavedException e) {
            e.printStackTrace();
            model.addAttribute("errormsg","Men nu gick det fel när din anmälan skulle sparas. Var snäll och försök igen.");
            return "publicregisterpatrol";
        }
    }


    public ModelAndView changeRegisteredPatrol() {

        return new ModelAndView("publicregisterpatrol");
    }

}
