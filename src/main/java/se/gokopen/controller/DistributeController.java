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
import se.gokopen.service.Distribute;
import se.gokopen.service.PatrolService;
import se.gokopen.service.StationService;

@RequestMapping("/distribute")
@Controller
public class DistributeController {
	@Autowired
	private PatrolService patrolService;
	@Autowired
	private StationService stationService;
	
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
		return new ModelAndView("distributestart","msg","Patrullerna är fördelade på alla kontroller för start.");
	}
}
