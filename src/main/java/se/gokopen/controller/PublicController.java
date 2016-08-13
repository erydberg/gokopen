package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Track;
import se.gokopen.service.ConfigService;
import se.gokopen.service.PatrolService;
import se.gokopen.service.TrackService;

@RequestMapping("/public")
@Controller
public class PublicController {
    
    @Autowired
    private ConfigService configService;
    
    @Autowired
    private TrackService trackService;
    
    @Autowired
    private PatrolService patrolService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String startPatrolsByTrack(HttpServletRequest request){
        request.setAttribute("config", configService.getCurrentConfig());
        request.setAttribute("tracks", trackService.getAllTracks());
        return "publicviewresult";
    }
    
    @RequestMapping(value="/bytrack/{id}")
    public ModelAndView startPatrolsByTrack(@PathVariable String id,HttpServletRequest request){
        
        Track track = null;
        try {
            track = trackService.getTrackById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TrackNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("selectedTrack", track.getTrackName());
        request.setAttribute("config", configService.getCurrentConfig());
        request.setAttribute("tracks", trackService.getAllTracks());
        List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
        return new ModelAndView("publicviewresult","patrols",patrols);
    }

}
