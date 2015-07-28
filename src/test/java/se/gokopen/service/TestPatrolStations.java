package se.gokopen.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;


public class TestPatrolStations {
    private static final String NAME_PATROL2 = "ATestPatrol2";
    private static final String NAME_PATROL1 = "BTestPatrol1";
    
    PatrolImpl patrol1;
    PatrolImpl patrol2;
    
    @Test
    public void shouldSortListofPatrolsAfterNoOfStations(){
        initTestData();
        
        List<PatrolImpl> patrols = new ArrayList<PatrolImpl>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        
        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        assertThat(patrols.get(0).getPatrolName(),is(equalTo(NAME_PATROL2)));
    }

    private void initTestData() {
        patrol1 = new PatrolImpl();
        patrol2 = new PatrolImpl();
        
        patrol1.setPatrolName(NAME_PATROL1);
        patrol2.setPatrolName(NAME_PATROL2);
       
        ScoreImpl score1 = new ScoreImpl();
        score1.setPatrol(patrol1);
        score1.setScorePoint(10);
        
        ScoreImpl score2 = new ScoreImpl();
        score2.setPatrol(patrol1);
        score2.setScorePoint(8);
        
        ScoreImpl score3 = new ScoreImpl();
        score3.setPatrol(patrol1);
        score3.setScorePoint(8);
        
        ScoreImpl score4 = new ScoreImpl();
        score4.setPatrol(patrol1);
        score4.setScorePoint(8);
        
        Set<ScoreImpl> scores = new LinkedHashSet<ScoreImpl>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);
        
        patrol1.setScores(scores);
        
        
        ScoreImpl score5 = new ScoreImpl();
        score5.setPatrol(patrol1);
        score5.setScorePoint(8);
        
        ScoreImpl score6 = new ScoreImpl();
        score6.setPatrol(patrol1);
        score6.setScorePoint(8);
        
        ScoreImpl score7 = new ScoreImpl();
        score7.setPatrol(patrol1);
        score7.setScorePoint(8);
        
        Set<ScoreImpl> scores2 = new LinkedHashSet<ScoreImpl>();
        scores2.add(score5);
        scores2.add(score6);
        scores2.add(score7);
        
        patrol2.setScores(scores2);
    }
}
