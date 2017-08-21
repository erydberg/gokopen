package se.gokopen.model;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class TestScore {
    
    @Test
    public void should_calculate_score_for_patrol() {
        Patrol patrol = new Patrol();
        patrol.setPatrolName("Eriks patrull");
        
        Station station1 = new Station();
        station1.setMaxScore(10);
        station1.setMinScore(0);
        station1.setStationName("Station 1");
        station1.setStationNumber(1);
        
        Station station2 = new Station();
        station2.setStationName("Station 2 - waypoint");
        station2.setStationNumber(2);
        station2.setWaypoint(true);
        
        Station station3 = new Station();
        station3.setStationName("Station 3");
        station3.setStationNumber(3);
        station3.setMaxScore(10);
        station3.setMinScore(0);
        
        Set<Score> scores = new LinkedHashSet<Score>();
        
        Score score1 = new Score();
        score1.setScorePoint(5);
        score1.setStation(station1);
        score1.setPatrol(patrol);
        score1.setStylePoint(1);
        scores.add(score1);
        
        Score score2 = new Score();
        score2.setPatrol(patrol);
        score2.setStation(station2);
        score2.setVisitedWaypoint(true);
        scores.add(score2);
        
        Score score3 = new Score();
        score3.setPatrol(patrol);
        score3.setStation(station3);
        score3.setScorePoint(6);
        score3.setStylePoint(1);
        scores.add(score3);
        
        patrol.setScores(scores);
        
        assertThat(patrol.getTotalScore(),equalTo(13));
        assertThat(patrol.getTotalReportedStations(),equalTo(3));

    }
}
