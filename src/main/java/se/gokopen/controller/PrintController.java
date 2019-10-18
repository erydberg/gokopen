package se.gokopen.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.model.Config;
import se.gokopen.model.Patrol;
import se.gokopen.model.StartStation;
import se.gokopen.model.Station;
import se.gokopen.model.Track;
import se.gokopen.service.ConfigService;
import se.gokopen.service.ExportService;
import se.gokopen.service.PatrolService;
import se.gokopen.service.StationService;
import se.gokopen.service.TrackService;

@RequestMapping("/print")
@Controller
public class PrintController {

    @Autowired
    private PatrolService patrolService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private StationService stationService;
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

    @RequestMapping(value = "")
    public String printStart(HttpServletRequest request) {
        return "printstart";
    }

    @RequestMapping(value = "/stationscorecards")
    public String printScoreCardsStart(HttpServletRequest request) {
        return "printstationscorecardsstart";
    }

    @RequestMapping(value = "/bytrack/{id}")
    public ModelAndView printScoreCardForTrack(@PathVariable String id, HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();
        try {
            Track track = trackService.getTrackById(Integer.parseInt(id));
            modelMap.addAttribute("selectedTrack", track.getTrackName());
            List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
            List<Station> stations = stationService.getAllStations();
            modelMap.addAttribute("stations", stations);
            modelMap.addAttribute("patrols", patrols);
        } catch (NumberFormatException | TrackNotFoundException e) {
            e.printStackTrace();
            modelMap.addAttribute("stations", Collections.emptyList());
            modelMap.addAttribute("patrols", Collections.emptyList());
        }
        return new ModelAndView("printscorecardstations", modelMap);
    }

    @GetMapping(value = "/patrolscorecards")
    public ModelAndView printPatrolCards() {
        ModelAndView model = preparePatrolsForPrint();
        model.setViewName("printpatrolcards");
        return model;
    }

    @RequestMapping(value = "/smallpatrolcards")
    public ModelAndView printSmallPatrolCards() {
        ModelAndView model = preparePatrolsForPrint();
        model.setViewName("printsmallpatrolcards");
        return model;
    }

    private ModelAndView preparePatrolsForPrint() {
        ModelAndView model = new ModelAndView();
        Config config = configService.getCurrentConfig();
        model.addObject("config", config);
        List<Station> stations = stationService.getAllStations();
        model.addObject("stations", stations);
        List<Patrol> patrols = patrolService.getAllPatrols();
        model.addObject("patrols", patrols);
        return model;
    }

    @GetMapping(value = "/patrolstartonstation")
    public ModelAndView printPatrolStartOnStation() {
        ModelAndView model = new ModelAndView();
        List<Station> stations = stationService.getAllStations();
        List<StartStation> startStations = new ArrayList<>();
        for (Station station : stations) {
            StartStation startStation = new StartStation();
            startStation.setStation(station);
            startStation.setPatrols(patrolService.getAllPatrolsByStartStation(station));
            startStations.add(startStation);
        }
        model.addObject("startStations", startStations);
        model.setViewName("printpatrolstartonstation");
        return model;
    }

    //TODO kunna anpassa avgränsningstecken så det fungerar bättre med olika språk i excel
    @GetMapping(value = "/export/shortreport")
    public void getShortReport(HttpServletResponse response) throws IOException {
        List<Track> tracks = trackService.getAllTracks();
        response.setContentType("text/csv");

        response.setHeader(
                "Content-Disposition",
                "attachment;filename=resultat-" + configService.getCurrentConfig().getName() + ".csv");
        ServletOutputStream out = response.getOutputStream();
        for (Track track : tracks) {
            out.println("Gren " + track.getTrackName());
            List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
            out.println("Plats;Patrull;Kår;Poäng;stilpoäng;totalt poäng");
            int i = 1;
            for (Patrol patrol : patrols) {
                out.println(
                        i + ";" + patrol.getPatrolName() + ";" + patrol.getTroop() + ";" + patrol.getTotalScorePoint()
                                + ";" + patrol.getTotalStylePoint() + ";" + patrol.getTotalScore());
                i = i + 1;
            }
        }
        out.flush();
        out.close();
    }

    //TODO kunna anpassa avgränsningstecken så det fungerar bättre med olika språk i excel
    @GetMapping(value = "/export/bigreport")
    public void getBigReport(HttpServletResponse response) throws IOException {
        List<Track> tracks = trackService.getAllTracks();
        List<Station> stations = stationService.getAllStations();
        response.setContentType("text/csv");

        response.setHeader(
                "Content-Disposition",
                "attachment;filename=resultat " + configService.getCurrentConfig().getName() + " alla kontroller" + ".csv");
        ServletOutputStream out = response.getOutputStream();
        ExportService exportService = new ExportService(stations);

        for (Track track : tracks) {
            out.println("Gren " + track.getTrackName());
            out.println(exportService.generateHeadlineFromStations());
            List<Patrol> patrols = patrolService.getAllPatrolsByTrack(track);
            int position = 1;
            for (Patrol patrol : patrols) {
                out.println(exportService.generateRowFor(position, patrol));
                position++;
            }
            out.println(";");
        }
        out.flush();
        out.close();
    }
}
