package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Station;
import se.gokopen.model.Status;
import se.gokopen.model.Track;
import se.gokopen.service.PatrolService;
import se.gokopen.service.StationService;
import se.gokopen.service.TrackService;

@RequestMapping("/patrol")
@Controller
public class PatrolController {

    @Autowired
    private PatrolService patrolService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private StationService stationService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Track.class, new TrackEditor(this.trackService));
        binder.registerCustomEditor(Station.class, new StationEditor(this.stationService));
    }

    @ModelAttribute("tracks")
    public List<Track> populateTracks() {
        return trackService.getAllTracks();
    }

    @ModelAttribute("stations")
    public List<Station> populateStations() {
        return stationService.getAllStations();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid Patrol patrol, BindingResult bindingResult, ModelMap model)
            throws PatrolNotSavedException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errormsg", "Några fält till behöver fyllas i.");
            return "patrol";
        }
        // Check to see if there is a saved patrol already since we otherwise empty the scores
        try {
            Patrol patrolOnDisc = patrolService.getPatrolById(patrol.getPatrolId());
            patrol.setScores(patrolOnDisc.getScores());

        } catch (PatrolNotFoundException e) {
            // not really a problem, just saving the new patrol
        }

        patrolService.savePatrol(patrol);

        // Return to list of existing patrols
        model.addAttribute("patrols", patrolService.getAllPatrols());
        return "patrollist";
    }

    @RequestMapping(value = "/admin/newpatrol", method = RequestMethod.GET)
    public ModelAndView newPatrol() {
        Patrol patrol = new Patrol();
        ModelMap map = new ModelMap();
        map.put("patrol", patrol);
        map.put("statuslist", Status.values());
        return new ModelAndView("patrol", map);
    }

    @RequestMapping(value = "/viewpatrol/{id}")
    public ModelAndView viewPatrol(@PathVariable String id, HttpServletRequest request) {
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.setAttribute("backurl", request.getContextPath() + "/patrol");
        return new ModelAndView("viewpatrol", "patrol", patrol);
    }

    @RequestMapping(value = "/viewpatrolfromlisttrack/{id}/track/{trackid}")
    public ModelAndView viewPatrolFromTrackList(@PathVariable String id, @PathVariable String trackid,
            HttpServletRequest request) {
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("backurl", request.getContextPath() + "/reports/bytrack/" + trackid);
        return new ModelAndView("viewpatrol", "patrol", patrol);

    }

    @RequestMapping(value = "/viewpatrolfrompatrollist/{id}")
    public ModelAndView viewPatrolFromPatrolList(@PathVariable String id, HttpServletRequest request) {
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        request.setAttribute("backurl", request.getContextPath() + "/reports/patrols");
        return new ModelAndView("viewpatrol", "patrol", patrol);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAllPatrols() {
        // Return to list of existing patrols
        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("patrollist", "patrols", patrols);
    }

    // Edit patrol
    @RequestMapping(value = "/admin/edit/{id}")
    public ModelAndView editPatrol(@PathVariable String id, HttpServletRequest request) {
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Station> stations = stationService.getAllStations();
        ModelMap map = new ModelMap();
        map.put("patrol", patrol);
        map.put("statuslist", Status.values());
        map.put("stations", stations);
        return new ModelAndView("patrol", map);
    }

    // Delete patrol
    @RequestMapping(value = "/admin/delete/{id}")
    public ModelAndView deletePatrol(@PathVariable String id, HttpServletRequest request) {
        try {
            patrolService.deletePatrolById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            e.printStackTrace();
        }

        // Return to list of existing patrols
        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("patrollist", "patrols", patrols);
    }
    
    //Resttjänster
    @RequestMapping(value="/admin/setpaid/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setPaid(@PathVariable String id) {
        try {
            Patrol patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setPaid(true);
            patrolService.savePatrol(patrol);
        }catch (PatrolNotSavedException | NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value="/admin/setnotpaid/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setNotPaid(@PathVariable String id) {
        try {
            Patrol patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setPaid(false);
            patrolService.savePatrol(patrol);
        }catch (PatrolNotSavedException | NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
    }
}
