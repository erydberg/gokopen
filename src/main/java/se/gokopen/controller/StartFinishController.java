package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.model.PatrolImpl;
import se.gokopen.service.PatrolService;



@RequestMapping("/startfinish")
@Controller
public class StartFinishController {
    
    @Autowired
    private PatrolService patrolService;
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView loadStartAndFinish(){
        List<PatrolImpl> patrols = patrolService.getAllPatrols();
        return new ModelAndView("startfinish", "patrols", patrols);
    }
    
    
    @RequestMapping(value="/sortbystatus",method=RequestMethod.GET)
    public ModelAndView sortTableByStatus(HttpServletRequest request){
        List<PatrolImpl> patrols = patrolService.getAllPatrolsSortedByStatus();
        return new ModelAndView("startfinish", "patrols", patrols);
    }
    
    @RequestMapping(value="/sortbytroop",method=RequestMethod.GET)
    public ModelAndView sortTableByTroop(HttpServletRequest request){
        List<PatrolImpl> patrols = patrolService.getAllPatrolsSortedByTroop();
        return new ModelAndView("startfinish", "patrols", patrols);
    }

    @RequestMapping(value="/sortbycompletedstations",method=RequestMethod.GET)
    public ModelAndView sortTableByCompletedStations(HttpServletRequest request){
        List<PatrolImpl> patrols = patrolService.getAllPatrolsSortedByNumberOfStations();
        return new ModelAndView("startfinish", "patrols", patrols);
    }
}
