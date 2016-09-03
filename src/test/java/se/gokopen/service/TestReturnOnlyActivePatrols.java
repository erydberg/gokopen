package se.gokopen.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import se.gokopen.model.Patrol;
import se.gokopen.model.Status;

public class TestReturnOnlyActivePatrols {
    
    //TODO funktionen är ändrad till att faktiskt ta med patruller som kommit i mål. Testerna borde ändras för att reflektera detta bättre än att bara ändra själva equalTo
    @Test
    public void shouldReturnOnlyActivePatrolsFromList(){
        Patrol patrol1 = new Patrol();
        Patrol patrol2 = new Patrol();
        Patrol patrol3 = new Patrol();
        Patrol patrol4 = new Patrol();
        
        patrol1.setStatus(Status.ACTIVE);
        patrol2.setStatus(Status.FINISHED);
        patrol3.setStatus(Status.REGISTERED);
        patrol4.setStatus(Status.RESIGNED);
        
        List<Patrol> patrols = new ArrayList<Patrol>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);
        
        PatrolService patrolService = new PatrolServiceImpl();
        
        List<Patrol> onlyActiveAndWaitingPatrols = patrolService.getActiveAndWaitingPatolsFromList(patrols);
        assertThat(onlyActiveAndWaitingPatrols.size(),is(equalTo(3)));
        
    }

    @Test
    public void shouldReturnOnlyActivePatrolsFromListAndWorkWithNullStatus(){
        Patrol patrol1 = new Patrol();
        Patrol patrol2 = new Patrol();
        Patrol patrol3 = new Patrol();
        Patrol patrol4 = new Patrol();
        
        patrol1.setStatus(Status.ACTIVE);
        patrol2.setStatus(Status.FINISHED);
        patrol3.setStatus(Status.REGISTERED);
        
        
        List<Patrol> patrols = new ArrayList<Patrol>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);
        
        PatrolService patrolService = new PatrolServiceImpl();
        
        List<Patrol> onlyActiveAndWaitingPatrols = patrolService.getActiveAndWaitingPatolsFromList(patrols);
        assertThat(onlyActiveAndWaitingPatrols.size(),is(equalTo(3)));
        
    }
}
