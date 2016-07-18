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
    
    @Test
    public void shouldReturnLastSavedTime(){
        Score score1 = new Score();
        Score score2 = new Score();
        Score score3 = new Score();
        Score score4 = new Score();
        Score score5 = new Score();
        
        Date now = new Date();
        score1.setScorePoint(1);
        
        Calendar myCalendar1 = new GregorianCalendar(2016, Calendar.MARCH, 25);
        Date date1 = myCalendar1.getTime();
        
        Calendar myCalendar2 = new GregorianCalendar(2016, Calendar.MARCH, 26, 12, 12, 12);
        Date date2 = myCalendar2.getTime();
        
        Calendar myCalendar3 = new GregorianCalendar(2016, Calendar.MARCH, 24);
        Date date3 = myCalendar3.getTime();
        
        Calendar myCalendar4 = new GregorianCalendar(2016, Calendar.MARCH, 26, 11, 11, 11);
        Date date4 = myCalendar4.getTime();
        
        Calendar myCalendar5 = new GregorianCalendar(2016, Calendar.MARCH, 26);
        Date date5 = myCalendar5.getTime();
                
        
        score1.setLastSaved(date1);
        score2.setLastSaved(date2);
        score3.setLastSaved(date3);
        score4.setLastSaved(date4);
        score5.setLastSaved(date5);
        
        Patrol patrol = new Patrol();
        Set<Score> scores = new LinkedHashSet<Score>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);
        scores.add(score5);
        patrol.setScores(scores );
        
        assertThat(patrol.getLatestScoreTime(),equalTo(date2));
    }

}
