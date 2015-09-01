package se.gokopen.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.Status;

public class TestReturnOnlyActivePatrols {
    
    @Test
    public void shouldReturnOnlyActivePatrolsFromList(){
        PatrolImpl patrol1 = new PatrolImpl();
        PatrolImpl patrol2 = new PatrolImpl();
        PatrolImpl patrol3 = new PatrolImpl();
        PatrolImpl patrol4 = new PatrolImpl();
        
        patrol1.setStatus(Status.ACTIVE);
        patrol2.setStatus(Status.FINISHED);
        patrol3.setStatus(Status.REGISTERED);
        patrol4.setStatus(Status.RESIGNED);
        
        List<PatrolImpl> patrols = new ArrayList<PatrolImpl>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);
        
        PatrolService patrolService = new PatrolServiceImpl();
        
        List<PatrolImpl> onlyActiveAndWaitingPatrols = patrolService.getActiveAndWaitingPatolsFromList(patrols);
        assertThat(onlyActiveAndWaitingPatrols.size(),is(equalTo(2)));
        
    }

    @Test
    public void shouldReturnOnlyActivePatrolsFromListAndWorkWithNullStatus(){
        PatrolImpl patrol1 = new PatrolImpl();
        PatrolImpl patrol2 = new PatrolImpl();
        PatrolImpl patrol3 = new PatrolImpl();
        PatrolImpl patrol4 = new PatrolImpl();
        
        patrol1.setStatus(Status.ACTIVE);
        patrol2.setStatus(Status.FINISHED);
        patrol3.setStatus(Status.REGISTERED);
        
        
        List<PatrolImpl> patrols = new ArrayList<PatrolImpl>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);
        
        PatrolService patrolService = new PatrolServiceImpl();
        
        List<PatrolImpl> onlyActiveAndWaitingPatrols = patrolService.getActiveAndWaitingPatolsFromList(patrols);
        assertThat(onlyActiveAndWaitingPatrols.size(),is(equalTo(2)));
        
    }
}
