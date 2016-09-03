package se.gokopen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.dao.StationNotFoundException;
import se.gokopen.model.Score;
import se.gokopen.model.Station;
import se.gokopen.service.ScoreService;
import se.gokopen.service.StationService;

@RequestMapping("/correctscore")
@Controller
public class CorrectScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private StationService stationService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView startPageCorrectScore(){
        List<Station> stations = stationService.getAllStations();
        ModelAndView model = new ModelAndView();
        model.addObject("stations", stations);
        model.setViewName("correctscorestartpage");
        model.addObject("selectedstation",new Station());
        return model;
    }

    @RequestMapping(path="/selectstation", method = RequestMethod.GET)
    public ModelAndView selectStation(Station selectedStation){
        List<Station> stations = stationService.getAllStations();
        ModelAndView model = new ModelAndView();
        try {
            selectedStation = stationService.getStationById(selectedStation.getStationId());
            model.addObject("stations", stations);
            model.addObject("selectedstation",selectedStation);

            //hämta poäng för den kontrollen
            if(SecurityChecker.isEditAllowedForCurrentUserOnStation(selectedStation)){
                List<Score> scores = scoreService.getScoreOnStation(selectedStation.getStationId());
                model.addObject("scores",scores);    
            }else{
                model.addObject("errormsg","Du kan inte ändra poäng på den här kontrollen.");
            }
        } catch (NumberFormatException | StationNotFoundException e) {
            model.addObject("selectedstation",new Station());
        } 
        model.setViewName("correctscorestartpage");
        return model;
    }

    @RequestMapping(path="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView fetchScoreForEdit(@PathVariable String id, HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        try {
            Score score = scoreService.getScoreById(Integer.parseInt(id));
            model.addObject("score",score);
        } catch (ScoreNotFoundException e) {
            model.addObject("errormsg","Hittar inte den poängen.");
        }
        model.setViewName("correctonescore");
        return model;
    }

    @RequestMapping(path="/save", method = RequestMethod.POST)
    public ModelAndView saveScore(Score score, BindingResult errors,
            HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView();
        model.setViewName("correctscorestartpage");
        try {
            scoreService.saveScore(score);
            model.addObject("alertmsg","Poängen är uppdaterade för patrullen.");
        } catch (ScoreNotSavedException e) {
            model.addObject("errormsg","Det gick inte att spara poängen.");
        }
        List<Station> stations = stationService.getAllStations();
        model.addObject("stations",stations);
        Station selectedStation;
        try {
            selectedStation = stationService.getStationById(score.getStation().getStationId());
            model.addObject("selectedstation",selectedStation);
            if(SecurityChecker.isEditAllowedForCurrentUserOnStation(selectedStation)){
                List<Score> scores = scoreService.getScoreOnStation(selectedStation.getStationId());
                model.addObject("scores",scores);    
            }else{
                model.addObject("errormsg","Du kan inte ändra poäng på den här kontrollen.");
            }
            
        } catch (StationNotFoundException e) {
            model.addObject("selectedstation",new Station());
        }
        
        return model;
    }
}
