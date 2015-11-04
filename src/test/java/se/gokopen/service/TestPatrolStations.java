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

import se.gokopen.model.Patrol;
import se.gokopen.model.Score;


public class TestPatrolStations {
    private static final String NAME_PATROL2 = "ATestPatrol2";
    private static final String NAME_PATROL1 = "BTestPatrol1";

    Patrol patrol1;
    Patrol patrol2;

    @Test
    public void shouldSortListofPatrolsAfterNoOfStations(){
        initTestData();

        List<Patrol> patrols = new ArrayList<Patrol>();
        patrols.add(patrol1);
        patrols.add(patrol2);

        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        assertThat(patrols.get(0).getPatrolName(),is(equalTo(NAME_PATROL2)));
    }

    @Test
    public void shouldSortHighestScoreHighest(){
        initTestData();

        List<Patrol> patrols = new ArrayList<Patrol>();
        patrols.add(patrol2);
        patrols.add(patrol1);

        Collections.sort(patrols, Collections.reverseOrder(new BeanComparator("totalScore")));
        assertThat(patrols.get(0).getPatrolName(),is(equalTo(NAME_PATROL1)));
    }

    private void initTestData() {
        patrol1 = new Patrol();
        patrol2 = new Patrol();

        patrol1.setPatrolName(NAME_PATROL1);
        patrol2.setPatrolName(NAME_PATROL2);

        Score score1 = new Score();
        score1.setPatrol(patrol1);
        score1.setScorePoint(10);

        Score score2 = new Score();
        score2.setPatrol(patrol1);
        score2.setScorePoint(8);

        Score score3 = new Score();
        score3.setPatrol(patrol1);
        score3.setScorePoint(8);

        Score score4 = new Score();
        score4.setPatrol(patrol1);
        score4.setScorePoint(8);

        Set<Score> scores = new LinkedHashSet<Score>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);

        patrol1.setScores(scores);


        Score score5 = new Score();
        score5.setPatrol(patrol1);
        score5.setScorePoint(8);

        Score score6 = new Score();
        score6.setPatrol(patrol1);
        score6.setScorePoint(8);

        Score score7 = new Score();
        score7.setPatrol(patrol1);
        score7.setScorePoint(8);

        Set<Score> scores2 = new LinkedHashSet<Score>();
        scores2.add(score5);
        scores2.add(score6);
        scores2.add(score7);

        patrol2.setScores(scores2);
    }
}
