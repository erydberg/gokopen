package se.gokopen.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.dao.PatrolDAO;
import se.gokopen.model.Patrol;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})

public class TestCriteriaVSQuery {
    
    @Autowired
    PatrolService patrolService;
    
    @Ignore
    @Test
    public void shouldReturnTheSameResults(){
        
        long startCriteria = System.currentTimeMillis();
        List<Patrol> patrolsCriteria = patrolService.getAllPatrolsCriteria();
        long stopCriteria = System.currentTimeMillis();
        long startQuery = System.currentTimeMillis();
        List<Patrol> patrolsQuery = patrolService.getAllPatrols();
        long stopQuery = System.currentTimeMillis();
        
        
        long totalQuery = stopQuery - startQuery;
        long totalCriteria = stopCriteria - startCriteria;
        
        System.out.println("query time: " + totalQuery + " " + patrolsQuery.size());
        System.out.println("criteria time: " + totalCriteria + " " + patrolsCriteria.size());
        
    }

}
