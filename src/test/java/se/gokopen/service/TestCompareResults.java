package se.gokopen.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import se.gokopen.model.Patrol;
import se.gokopen.model.Score;
import se.gokopen.model.Station;

public class TestCompareResults {
    
    private Patrol patrol1;
    private Patrol patrol2;
    
    
    @Before
    public void init(){
        patrol1 = new Patrol();
        patrol2 = new Patrol();
    }
    
    @Test
    public void shouldCalucalateTheRightScore(){
        patrol1 = addScores(patrol1);
        assertThat(patrol1.getTotalScore(),is(equalTo(17)));
    }

    
    @Test
    public void shouldCalculateTheRightScoreWithStyleScore(){
        patrol1 = addScores(patrol1);
        addStylePointsToScores(patrol1.getScores());
        int totalStylePoints = patrol1.getScores().size();
        assertThat(patrol1.getTotalScore(),is(equalTo(17 + totalStylePoints)));
    }
    
    @Test
    public void shouldCompareTwoPatrolsAndFindOneWithMorePointsToBeTheWinner(){
        patrol1 = addScores(patrol1);
        patrol1 = addMoreScores(patrol1);
        patrol2 = addScores(patrol2);
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
    }
    
    @Test
    public void twoPatrolsWithTheSameScoreButOneWithMoreStylePointsShouldBeLast(){
        patrol1 = addScores(patrol1);
        patrol1 = addMoreScores(patrol1);
        patrol2 = addScores(patrol2);
        addStylePointsToScores(patrol2.getScores());
        
        //same total score
        assertThat(patrol1.getTotalScore(),is(equalTo(patrol2.getTotalScore())));
        //winner is the patrol with more scores and less style-points
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
    }
    
    @Test
    public void shouldFindAWinnerAmongPatrolsWithTheSameScoreBasedOn10Points() {
        Station station1 = new Station();
        Station station2 = new Station();
        Station station3 = new Station();
        
        station1.setMaxScore(10);
        station2.setMaxScore(10);
        station3.setMaxScore(10);
        
        
        Score score1 = new Score();
        Score score2 = new Score();
        Score score3 = new Score();
        Score score4 = new Score();
        Score score5 = new Score();
        Score score6 = new Score();
        
        score1.setStation(station1);
        score2.setStation(station2);
        score3.setStation(station3);
        score4.setStation(station1);
        score5.setStation(station2);
        score6.setStation(station3);
        
        
        score1.setScorePoint(10);
        score2.setScorePoint(8);
        score3.setScorePoint(10);
        
        Set<Score> scores = new LinkedHashSet<Score>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        patrol1.setScores(scores);
        
        score4.setScorePoint(9);
        score5.setScorePoint(9);
        score6.setScorePoint(10);
        
        Set<Score> moreScores = new LinkedHashSet<Score>();
        moreScores.add(score4);
        moreScores.add(score5);
        moreScores.add(score6);
        patrol2.setScores(moreScores);
        
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
    }
    
    private Patrol addScores(Patrol patrol) {
        patrol.setScores(createBasicScores());
        return patrol;
    }
    
    private void addStylePointsToScores(Set<Score> scores){
        for(Score score:scores){
            score.setStylePoint(1);
        }
    }
    
    private Patrol addMoreScores(Patrol patrol){
        Score score = new Score();
        score.setScorePoint(2);
        patrol.getScores().add(score);
        
        return patrol;
    }
    
    private Set<Score> createBasicScores(){
        Score score1 = new Score();
        score1.setScorePoint(9);
        Score score2 = new Score();
        score2.setScorePoint(8);
        
        Set<Score> scores1 = new LinkedHashSet<Score>();
        scores1.add(score1);
        scores1.add(score2);
        return scores1;
    }
}
