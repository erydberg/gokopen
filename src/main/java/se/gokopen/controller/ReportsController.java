package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Station;
import se.gokopen.model.Track;
import se.gokopen.service.PatrolService;
import se.gokopen.service.ScoreService;
import se.gokopen.service.StationService;
import se.gokopen.service.TrackService;

@RequestMapping("/reports")
@Controller
public class ReportsController {
	
	//Skjuter in PatrolService
	@Autowired
	private PatrolService patrolService;
	@Autowired
	private TrackService trackService;
	@Autowired
	private StationService stationService;
	@Autowired
	private ScoreService scoreService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Station.class, new StationEditor(this.stationService));
		binder.registerCustomEditor(Patrol.class, new PatrolEditor(this.patrolService));
		binder.registerCustomEditor(Track.class, new TrackEditor(this.trackService));
    }
	
	@ModelAttribute("tracks")
    public List<Track> populateTracks() {
		return trackService.getAllTracks();
	}
	
	@RequestMapping(value="/patrols")
	public ModelAndView viewPatrols(HttpServletRequest request){
		List<Patrol> patrols = patrolService.getAllPatrols();
		return new ModelAndView("viewpatrollist","patrols",patrols);
	}
	
	@RequestMapping(value="/bytrack")
	public String startPatrolsByTrack(HttpServletRequest request){
	    return "viewpatrolsbytrack";
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
		request.setAttribute("trackid", track.getTrackId());
		request.setAttribute("selectedTrack", track.getTrackName());
		request.setAttribute("backurl", request.getContextPath() + "/reports/bytrack/" + track.getTrackId());
		List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
		return new ModelAndView("viewpatrolsbytrack","patrols",patrols);
	}
}
