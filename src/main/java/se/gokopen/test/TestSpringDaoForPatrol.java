package se.gokopen.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.gokopen.dao.PatrolDAO;
import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.model.PatrolImpl;

public class TestSpringDaoForPatrol {

	private static ApplicationContext context;

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("se/gokopen/hibernate/spring-hibernate.xml");

		PatrolDAO patrolDao = (PatrolDAO)context.getBean("PatrolDAO");
		
		
//		PatrolImpl patrol = new PatrolImpl();
//		patrol.setPatrolName("Eriks testSpringPatrull");
//		PatrolDAO patrolDao = (PatrolDAO)context.getBean("PatrolDAO");
//		patrolDao.save(patrol);

//		patrolDao.deleteById(1);
		
		PatrolImpl patrol = null;
		try {
			patrol = (PatrolImpl) patrolDao.getById(2);
		} catch (PatrolNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Patrull: " + patrol.getPatrolName());
		
		System.out.println("Alla");
		List<PatrolImpl> patrols = patrolDao.getAllPatrols();
		for(int i=0;i<patrols.size();i++){
			PatrolImpl pat = patrols.get(i);
			System.out.println("Patroll: " + pat.getPatrolName() + pat.getPatrolId());
			
		}
		
	}

}
