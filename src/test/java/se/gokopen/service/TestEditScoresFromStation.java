package se.gokopen.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.model.Patrol;
import se.gokopen.model.Score;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})


public class TestEditScoresFromStation {


    @Autowired
    private ScoreService scoreService;

    @Ignore
    @Test
    public void shouldReturnAllScoresOnOneStation(){
        List<Score> scores = scoreService.getScoreOnStation(2);
        System.out.println("antal poäng: " + scores.size());
        for(Score score:scores){
            System.out.println("poäng: " + score.getScorePoint());
            Patrol patrol = score.getPatrol();
            if(patrol==null){
                System.out.println("patrullen är ingen");
            }else{
                System.out.println("patrullen är " + patrol.getPatrolName());
            }

        }
        
    }

}
