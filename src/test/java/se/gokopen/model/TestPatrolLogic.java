package se.gokopen.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestPatrolLogic {
    private static final String STATION2 = "Station2";
    Patrol patrol;
    Date date2;
    @Test
    public void shouldReturnLastSavedTime(){

        setupScores();

        assertThat(patrol.getLatestScore().getLastSaved(),equalTo(date2));
    }

    @Test
    public void should_return_name_of_last_saved_scores_station() {
        setupScores();
        assertThat(patrol.getLatestScore().getStation().getStationName(),equalTo(STATION2));
    }



    private void setupScores() {
        Station station1 = new Station();
        Station station2 = new Station();
        Station station3 = new Station();
        Station station4 = new Station();
        Station station5 = new Station();

        station1.setStationName("Station1");
        station2.setStationName(STATION2);
        station3.setStationName("Station3");
        station4.setStationName("Station4");
        station5.setStationName("Station5");

        Score score1 = new Score();
        Score score2 = new Score();
        Score score3 = new Score();
        Score score4 = new Score();
        Score score5 = new Score();

        score1.setScorePoint(1);

        Calendar myCalendar1 = new GregorianCalendar(2016, Calendar.MARCH, 25);
        Date date1 = myCalendar1.getTime();

        Calendar myCalendar2 = new GregorianCalendar(2016, Calendar.MARCH, 26, 12, 12, 12);
        date2 = myCalendar2.getTime();

        Calendar myCalendar3 = new GregorianCalendar(2016, Calendar.MARCH, 24);
        Date date3 = myCalendar3.getTime();

        Calendar myCalendar4 = new GregorianCalendar(2016, Calendar.MARCH, 26, 11, 11, 11);
        Date date4 = myCalendar4.getTime();

        Calendar myCalendar5 = new GregorianCalendar(2016, Calendar.MARCH, 26);
        Date date5 = myCalendar5.getTime();


        score1.setLastSaved(date1);
        score1.setStation(station1);
        score2.setLastSaved(date2);
        score2.setStation(station2);
        score3.setLastSaved(date3);
        score3.setStation(station3);
        score4.setLastSaved(date4);
        score4.setStation(station4);
        score5.setLastSaved(date5);
        score5.setStation(station5);

        patrol = new Patrol();
        Set<Score> scores = new LinkedHashSet<Score>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);
        scores.add(score5);
        patrol.setScores(scores );
    }

}
