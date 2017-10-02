package se.gokopen.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.model.Config;
import se.gokopen.model.Patrol;
import se.gokopen.model.StartStation;
import se.gokopen.model.Station;
import se.gokopen.model.Track;
import se.gokopen.service.ConfigService;
import se.gokopen.service.PatrolService;
import se.gokopen.service.ScoreService;
import se.gokopen.service.StationService;
import se.gokopen.service.TrackService;

@RequestMapping("/print")
@Controller
public class PrintController { 
	
	//Skjuter in PatrolService
	@Autowired
	private PatrolService patrolService;
	@Autowired
	private TrackService trackService;
	@Autowired
	private StationService stationService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
    private ConfigService configService;
	
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
	
	@RequestMapping(value="")
	public String printStart(HttpServletRequest request){
		return "printstart";
	}
	@RequestMapping(value="/stationscorecards")
    public String printScoreCardsStart(HttpServletRequest request){
        return "printstationscorecardsstart";
    }
	
	
	
	@RequestMapping(value="/bytrack/{id}")
	public ModelAndView printScoreCardForTrack(@PathVariable String id,HttpServletRequest request){
		Track track = null;
		try {
			track = trackService.getTrackById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (TrackNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("selectedTrack", track.getTrackName());
		List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
		List<Station> stations = stationService.getAllStations();
		request.setAttribute("stations", stations);
		
		return new ModelAndView("printscorecardstations","patrols",patrols);
	}
	
	@RequestMapping(value="/patrolscorecards", method=RequestMethod.GET)
	public ModelAndView printPatrolCards(){
	    ModelAndView model = preparePatrolsForPrint();
	    model.setViewName("printpatrolcards");
	    return model;
	}

	@RequestMapping(value="/smallpatrolcards", method=RequestMethod.GET)
    public ModelAndView printSmallPatrolCards() {
        ModelAndView model = preparePatrolsForPrint();
        model.setViewName("printsmallpatrolcards");
        return model;
    }
	
	private ModelAndView preparePatrolsForPrint() {
        ModelAndView model = new ModelAndView();
	    Config config = configService.getCurrentConfig();
	    model.addObject("config",config);
	    List<Station> stations = stationService.getAllStations();
	    model.addObject("stations",stations);
	    List<Patrol> patrols = patrolService.getAllPatrols();
	    model.addObject("patrols",patrols);
        return model;
    }
	
	
	@RequestMapping(value="/patrolstartonstation", method=RequestMethod.GET)
	public ModelAndView printPatrolStartOnStation() {
		ModelAndView model = new ModelAndView();
		List<Station> stations = stationService.getAllStations();
		List<StartStation> startStations = new ArrayList<StartStation>();
		for(Station station:stations) {
			StartStation startStation = new StartStation();
			startStation.setStation(station);
			startStation.setPatrols(patrolService.getAllPatrolsByStartStation(station));
			startStations.add(startStation);
		}
		model.addObject("startStations",startStations);
		model.setViewName("printpatrolstartonstation");
		return model;
		
	}
	
}
