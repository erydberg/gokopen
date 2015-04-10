package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.dao.StationNotFoundException;
import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Station;
import se.gokopen.model.Track;
import se.gokopen.service.PatrolService;
import se.gokopen.service.ScoreService;
import se.gokopen.service.StationService;
import se.gokopen.service.TrackService;

@RequestMapping("/score")
@Controller
public class ScoreController {

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
		binder.registerCustomEditor(Station.class, new StationEditor(
				this.stationService));
		binder.registerCustomEditor(PatrolImpl.class, new PatrolEditor(
				this.patrolService));
		binder.registerCustomEditor(Track.class, new TrackEditor(
				this.trackService));
	}

	@ModelAttribute("tracks")
	public List<Track> populateTracks() {
		return trackService.getAllTracks();
	}

	@ModelAttribute("stations")
	public List<Station> populateStations() {
		return stationService.getAllStations();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView startScore() {
		ScoreImpl score = new ScoreImpl();
		return new ModelAndView("reportscore", "score", score);
	}

	@RequestMapping(value = "/selectstation", method = RequestMethod.POST)
	public ModelAndView selectStation(ScoreImpl score, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) {
		if(SecurityChecker.isEditAllowedForCurrentUser(score)){
			List<PatrolImpl> patrols = patrolService.getAllPatrolsLeftOnStation(score.getStation().getStationId());
			request.setAttribute("patrols", patrols);
			return new ModelAndView("reportscore", "score", score);
		}else{
			request.setAttribute("errormsg", "Du har inte behörighet att ge poäng på denna kontroll.");
			score = new ScoreImpl();
			return new ModelAndView("reportscore", "score", score);
		}
	}
	
	//Används nog inte
	@RequestMapping(value = "/editscore/{id}")
	public ModelAndView editScore(@PathVariable String id, HttpServletRequest request) {
		ScoreImpl score = null;
		try {
			score = scoreService.getScoreById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScoreNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("saveurl", request.getContextPath() + "/score/savescore");
		request.setAttribute("patrol", score.getPatrol());
		return new ModelAndView("reportscore", "score", score);

	}


	@RequestMapping(value = "/editscorefrompatrol/{id}/returnto/{patrolid}")
	public ModelAndView editScoreFromPatrolView(@PathVariable String id, @PathVariable String patrolid, HttpServletRequest request) {
		ScoreImpl score = null;
		try {
			score = scoreService.getScoreById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ScoreNotFoundException e) {
		    request.setAttribute("errormsg", "Hittar inte poängen i systemet.");
            return new ModelAndView("start");
		}
		
		Integer trackid = score.getPatrol().getTrack().getTrackId();
		request.setAttribute("backurl",
		        request.getContextPath() + "/patrol/viewpatrolfromlisttrack/" + patrolid
				+ "/track/" + trackid);

		if(SecurityChecker.isEditAllowedForCurrentUser(score)){
			return new ModelAndView("editscore", "score", score);	
		}else{
			//Får inte redigera därmed tillbaka till patrullen
			PatrolImpl patrol = null;
			try {
				patrol = patrolService.getPatrolById(Integer.parseInt(patrolid));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (PatrolNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("errormsg", "Du har inte behörighet att ändra denna poäng.");
			request.setAttribute("backurl",	request.getContextPath() + "/reports/patrols");
			return new ModelAndView("viewpatrol","patrol",patrol);		
		}
	}

	@RequestMapping(value = "/viewscore/{id}")
	public ModelAndView viewScore(@PathVariable String id,
			HttpServletRequest request) {
		ScoreImpl score = null;
		try {
			score = scoreService.getScoreById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ScoreNotFoundException e) {
			e.printStackTrace();
		}
		return new ModelAndView("viewscore", "score", score);
	}

	@RequestMapping(value = "/savescore", method = RequestMethod.POST)
	public ModelAndView saveScore(ScoreImpl score, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) {

		if(score.getPatrol()==null){
			request.setAttribute("errormsg", "Du måste välja en patrull innan du sparar poängen.");
		}else{
		try {
				scoreService.saveScore(score, false);
				request.setAttribute("oldPatr", score.getPatrol());
				request.setAttribute("oldScore", score);

			} catch (ScoreNotSavedException e) {
			    request.setAttribute("errormsg",e.getErrorMsg());
			}
		}

		ScoreImpl scorenew = new ScoreImpl();
		Station stationTest = new Station();
		try {
			stationTest = stationService.getStationById(score.getStation()
					.getStationId());
		} catch (StationNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scorenew.setStation(stationTest);
		List<PatrolImpl> patrols = patrolService
		.getAllPatrolsLeftOnStation(score.getStation().getStationId());
		// List<PatrolImpl> patrols = patrolService.getAllPatrols();
		request.setAttribute("patrols", patrols);

		return new ModelAndView("reportscore", "score", scorenew);
	}

	@RequestMapping(value = "/savescorefrompatrol", method = RequestMethod.POST)
	public ModelAndView saveScoreFromPatrol(ScoreImpl score,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			scoreService.saveScore(score, true);
		} catch (ScoreNotSavedException e) {
		    request.setAttribute("errormsg",e.getErrorMsg());
		}

		Integer patrolId = score.getPatrol().getPatrolId();
		PatrolImpl patrolReloaded = null;
		try {
			patrolReloaded = patrolService.getPatrolById(patrolId);
		} catch (PatrolNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("backurl", request.getContextPath() + "/reports/patrols"); // för att det är vanligast....
		return new ModelAndView("viewpatrol", "patrol", patrolReloaded);
	}

	@RequestMapping(value = "/delete/{id}/patrolid/{patrolid}")
	public ModelAndView deleteScore(@PathVariable String id,
			@PathVariable String patrolid, HttpServletRequest request) {
		// return to viewpatrol with value to back-link
		ScoreImpl score = null;
		PatrolImpl patrol = null;
		try {
			score = scoreService.getScoreById(Integer.parseInt(id));
			System.out.println("deleting: found score: " + score.getScoreId() + " score: " + score.getScorePoint() + " at station: " + score.getStation().getStationName());
			patrol = patrolService.getPatrolById(Integer.parseInt(patrolid));
			patrol.deleteScore(score);
			patrolService.savePatrol(patrol);
		} catch (NumberFormatException e1) {
			System.out.println("NumberFormatException: Problem deleting score " + id);
			e1.printStackTrace();
		} catch (ScoreNotFoundException e1) {
			System.out.println("ScoreNotFoundException: Problem deleting score " + id);
			e1.printStackTrace();
			request.setAttribute("errormsg", "Hittar inte poängen att ta bort.");
            return new ModelAndView("start");
		} 
		catch (PatrolNotFoundException e) {
			System.out.println("PatrolNotFoundException: Problem deleting score " + id);
			e.printStackTrace();
			request.setAttribute("errormsg", "Hittar inte patrullen att ta bort poängen från.");
            return new ModelAndView("start");
		} catch (PatrolNotSavedException e) {
			System.out.println("PatrolNotSavedException: Problem deleting score " + id);
			e.printStackTrace();
			request.setAttribute("errormsg", "Kunde inte spara patrullen och dess poängt.");
            return new ModelAndView("start");
		}

		request.setAttribute("backurl", request.getContextPath() + "/reports/patrols");
		return new ModelAndView("viewpatrol", "patrol", patrol);
	}
}
