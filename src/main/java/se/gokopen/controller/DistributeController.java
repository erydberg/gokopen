package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Station;
import se.gokopen.model.Track;
import se.gokopen.service.Distribute;
import se.gokopen.service.PatrolService;
import se.gokopen.service.StationService;
import se.gokopen.service.TrackService;

@RequestMapping("/admin/distribute")
@Controller
public class DistributeController {
	@Autowired
	private PatrolService patrolService;
	@Autowired
	private StationService stationService;
	@Autowired
	private TrackService trackService;
	
	@RequestMapping(value="")
	public String distributeStart(HttpServletRequest request){
		return "distributestart";
	}
	
	@RequestMapping(value="all")
	public ModelAndView distributeAllPatrolsOnAllStations(HttpServletRequest request){
		List<Station> stations = stationService.getAllStations();
		List<Patrol> patrols = patrolService.getAllPatrols();
		Distribute.patrolsOnStations(patrols, stations);
		try {
			patrolService.saveAllpatrols(patrols);
		} catch (PatrolNotSavedException e) {
			e.printStackTrace();
			return new ModelAndView("distributestart","errormsg","Något gick fel när patrullerna skulle fördelas på kontrollerna");
		}
		return new ModelAndView("distributestart","msg", patrols.size() + " patruller är fördelade på " + stations.size() + " kontroller för start.");
	}
	
	@RequestMapping(value="basedontrack")
	public ModelAndView distributeAllPatrolsBasedOnTrack(HttpServletRequest request) {
		List<Station> stations = stationService.getAllStations();
		List<Track> tracks = trackService.getAllTracks();
		int patrolCounter = 0;
		for(Track track:tracks) {
			List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
			patrolCounter = patrolCounter + patrols.size();
			Distribute.patrolsOnStations(patrols, stations);
			try {
				patrolService.saveAllpatrols(patrols);
			} catch (PatrolNotSavedException e) {
				e.printStackTrace();
				return new ModelAndView("distributestart","errormsg","Något gick fel när patrullerna skulle fördelas på kontrollerna");
			}
		}
		return new ModelAndView("distributestart","msg", patrolCounter + " patruller är fördelade på " + stations.size() + " kontroller för start.");
	}
}
