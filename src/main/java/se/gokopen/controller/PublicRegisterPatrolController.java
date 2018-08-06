package se.gokopen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Track;
import se.gokopen.service.ConfigService;
import se.gokopen.service.PatrolService;
import se.gokopen.service.TrackService;

@RequestMapping("/register")
@Controller
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
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showRegisterPatrolForm(HttpServletRequest request) {
        ModelMap map = new ModelMap();
        map.put("config", configService.getCurrentConfig());
        map.put("tracks", trackService.getAllTracks());
        map.put("patrol", new Patrol());
        return new ModelAndView("publicregisterpatrol",map);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView savePatrol(@ModelAttribute("patrol")Patrol patrol, ModelMap model) {
        
        try {
            patrolService.savePatrol(patrol);
        } catch (PatrolNotSavedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelMap map = new ModelMap();
        map.put("status", "patrull: " + patrol.getPatrolName() + " klass: " + patrol.getTrack().getTrackName());
        map.put("config", configService.getCurrentConfig());
        map.put("tracks", trackService.getAllTracks());
        map.put("patrol", new Patrol());
        return new ModelAndView("publicregisterpatrol", map);
    }
    
    
    public ModelAndView changeRegisteredPatrol() {
        
        return new ModelAndView("publicregisterpatrol");
    }

}
