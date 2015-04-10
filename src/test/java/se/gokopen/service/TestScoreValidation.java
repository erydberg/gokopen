package se.gokopen.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.dao.StationNotFoundException;
import se.gokopen.dao.StationNotSavedException;
import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Station;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})


public class TestScoreValidation {

    private Station station1;
    private Station station2;
    private PatrolImpl patrol1;
    private PatrolImpl patrol2;
    

    @Autowired
    private ScoreService scoreService;
    
    @Autowired
    private PatrolService patrolService;
    
    @Autowired
    private StationService stationService;
    
    @Before
    public void setup() throws PatrolNotSavedException, StationNotSavedException{
               
    }

    @Test
    public void shouldNotSaveIfScoreAlreadySaved() throws PatrolNotSavedException, StationNotSavedException{
        patrol1 = new PatrolImpl();
        patrol2 = new PatrolImpl();
        station1 = new Station();
        station2 = new Station();
        
        patrol1.setPatrolName("TestPatrol1");
        patrol2.setPatrolName("TestPatrol2");
        patrolService.savePatrol(patrol1);
        patrolService.savePatrol(patrol2);
        
        station1.setStationName("TestStation88");
        stationService.saveStation(station1);
        
//        station1.setStationNumber(88);
        station2.setStationName("TestStation99");
//        station2.setStationId(99);
        
        stationService.saveStation(station2);
        
        ScoreImpl score1 = new ScoreImpl();
        score1.setPatrol(patrol1);
        score1.setStation(station1);
        score1.setScorePoint(10);
        
        try{
            scoreService.saveScore(score1, false);    
        }catch(ScoreNotSavedException e){
            System.out.println("meddelande " + e.getErrorMsg());
        }
        
        
        assertNotNull(score1.getScoreId());
        
        ScoreImpl score2 = new ScoreImpl();
        score2.setPatrol(patrol2);
        score2.setStation(station1);
        score2.setScorePoint(8);
        try {
            scoreService.saveScore(score2, false);
        } catch (ScoreNotSavedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertNotNull(score2.getScoreId());
        
        ScoreImpl score3 = new ScoreImpl();
        score3.setPatrol(patrol2);
        score3.setStation(station1);
        score3.setScorePoint(9);
        try {
            scoreService.saveScore(score3, false);
        } catch (ScoreNotSavedException e) {
            assertNotNull(e);
        }
        
        assertNull(score3.getScoreId());

        try {
            scoreService.deleteScore(score1);
            scoreService.deleteScore(score2);
            
            patrolService.deletePatrol(patrol1);
            patrolService.deletePatrol(patrol2);
            
            stationService.deleteStation(station1);
            stationService.deleteStation(station2);
            
        } catch (ScoreNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PatrolNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (StationNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
    }

}
