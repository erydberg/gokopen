package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Status;
import se.gokopen.service.PatrolService;



@RequestMapping("/startfinish")
@Controller
public class StartFinishController {

    @Autowired
    private PatrolService patrolService;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView loadStartAndFinish(){
        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("startfinish", "patrols", patrols);
    }


    @RequestMapping(value="/sortbystatus",method=RequestMethod.GET)
    public ModelAndView sortTableByStatus(HttpServletRequest request){
        List<Patrol> patrols = patrolService.getAllPatrolsSortedByStatus();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/sortbytroop",method=RequestMethod.GET)
    public ModelAndView sortTableByTroop(HttpServletRequest request){
        List<Patrol> patrols = patrolService.getAllPatrolsSortedByTroop();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/sortbycompletedstations",method=RequestMethod.GET)
    public ModelAndView sortTableByCompletedStations(HttpServletRequest request){
        List<Patrol> patrols = patrolService.getAllPatrolsSortedByNumberOfStations();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/sortbytrack",method=RequestMethod.GET)
    public ModelAndView sortTableByTrack(HttpServletRequest request){
        List<Patrol> patrols = patrolService.getAllPatrolsSortedByTrack();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/sortbyscore",method=RequestMethod.GET)
    public ModelAndView sortTableByScore(HttpServletRequest request){
        List<Patrol> patrols = patrolService.getAllPatrolsSortedByScore();
        return new ModelAndView("startfinish", "patrols", patrols);
    }


    //Rest-version of move to active
    @RequestMapping(value="/activate/{id}" , method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable String id, HttpServletRequest request){
        try {
            Patrol patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.ACTIVE);
            patrolService.savePatrol(patrol);
        } catch (PatrolNotSavedException | NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value="/finished/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finished(@PathVariable String id, HttpServletRequest request){
        try {
            Patrol patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.FINISHED);
            patrolService.savePatrol(patrol);
        } catch (PatrolNotSavedException | NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value="/resigned/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resigned(@PathVariable String id, HttpServletRequest request){
        try {
            Patrol patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.RESIGNED);
            patrolService.savePatrol(patrol);
        } catch (PatrolNotSavedException | NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/registered/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registered(@PathVariable String id, HttpServletRequest request){
        try {
            Patrol patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.REGISTERED);
            patrolService.savePatrol(patrol);
        } catch (PatrolNotSavedException | NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value="/viewpatrol/{id}")
    public ModelAndView viewPatrolFromPatrolList(@PathVariable String id, HttpServletRequest request){
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
        } catch (NumberFormatException | PatrolNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("backurl", request.getContextPath() + "/startfinish");
        return new ModelAndView("viewpatrol","patrol",patrol);
        
    }
    
    
    //gamla, länkbaserade lösningen - borde kunna tas bort!
    @RequestMapping(value="/movetoactive/{id}",method=RequestMethod.GET)
    public ModelAndView moveToActive(@PathVariable String id, HttpServletRequest request){
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.ACTIVE);
            patrolService.savePatrol(patrol);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotSavedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/movetofinished/{id}",method=RequestMethod.GET)
    public ModelAndView moveToFinish(@PathVariable String id, HttpServletRequest request){
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.FINISHED);
            patrolService.savePatrol(patrol);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotSavedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/movetoresigned/{id}",method=RequestMethod.GET)
    public ModelAndView moveToResigned(@PathVariable String id, HttpServletRequest request){
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.RESIGNED);
            patrolService.savePatrol(patrol);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotSavedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/movetoregistered/{id}",method=RequestMethod.GET)
    public ModelAndView moveToRegistered(@PathVariable String id, HttpServletRequest request){
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(id));
            patrol.setStatus(Status.REGISTERED);
            patrolService.savePatrol(patrol);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotSavedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Patrol> patrols = patrolService.getAllPatrols();
        return new ModelAndView("startfinish", "patrols", patrols);
    }
}
