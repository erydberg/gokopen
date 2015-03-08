package se.gokopen.test;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import se.gokopen.hibernate.HibernateUtil;
import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Station;
import se.gokopen.model.Track;

public class TestSavePatrolWithScore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSavePatrolWithScore test = new TestSavePatrolWithScore();
		Track track = new Track();
		track.setTrackName("Utmanare");
		test.saveTrack(track);

		//Skapa station
		Station station = new Station();
		station.setMaxScore(10);
		station.setMinScore(0);
		station.setStationContact("Erik Kontakt");
		station.setStationName("Livlina");
		station.setStationNumber(1);
		station.setStationPhonenumber("021-121212");
		test.saveStation(station);
		
		Station station2 = new Station();
		station2.setMaxScore(10);
		station2.setMinScore(0);
		station2.setStationContact("Lotta Kontakt");
		station2.setStationName("Sjukvård");
		station2.setStationNumber(2);
		station2.setStationPhonenumber("021-121212");
		test.saveStation(station2);
//		
		
//		Track track = test.getTrackByName("Utmanare");
		
		
		PatrolImpl patrol = new PatrolImpl();
		patrol.setPatrolName("Kalles kulor");
		patrol.setTrack(track);
		patrol.setTroop("Härryda scoutkår");
	
//		test.savePatrol(patrol);
//		


		
		//Hämta patrull
//		PatrolImpl patrol2 = test.getPatrolById(2);
		
		//Hämta station
		Station stationCurrent = test.getStationByOrder(1);
		Station stationCurrent2 = test.getStationByOrder(2);
		
		//Lägga till poäng
		ScoreImpl score = new ScoreImpl();
		score.setScorePoint(8);
		score.setStylePoint(1);
		score.setStation(stationCurrent);
//		test.saveScore(score);
		
		ScoreImpl score2 = new ScoreImpl();
		score2.setScorePoint(1);
		score2.setStylePoint(0);
		score2.setStation(stationCurrent2);
		
	
		
//		ScoreImpl score2 = new ScoreImpl();
//		score2.setScorePoint(1);
//		score2.setScoreId(0);
//		score2.setStation(stationCurrent);
//		test.saveScore(score);
		
		
		Set<ScoreImpl> scores = new LinkedHashSet<ScoreImpl>();
		scores.add(score);
		scores.add(score2);
	//	spara till patrullen
		patrol.setScores(scores);
//		System.out.println("scoreid: " + score.getScoreId());
		test.savePatrol(patrol);
		
	}

	@SuppressWarnings("unused")
    private Track getTrackByName(String name) {
		Session session = null;
		Track track = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Query query = session.createQuery("from Track tr where tr.trackName=" + name);
//			Query query = session.createQuery("from Station station where station.stationNumber=" + order);
			track = (Track)query.list().get(0);
			System.out.println("Fetched station: " + track.getTrackId());
			session.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println("Could't fetch track with name " + name);
			System.out.println(e.toString());
		}finally{
			session.close();
		}
		return track;
	}

	@SuppressWarnings("unused")
    private void saveScore(ScoreImpl score){
		System.out.println("Spara score");
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.saveOrUpdate(score);
			session.getTransaction().commit();
			
		}catch(Exception e){
			// TODO: handle exception
			System.out.println( e.getMessage() );
		}finally{
			session.close();  
		}
	}

	
	public Station getStationByOrder(int order){
		Session session = null;
		Station station = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Query query = session.createQuery("from Station station where station.stationNumber=" + order);
			station = (Station)query.list().get(0);
			System.out.println("Fetched station: " + station.getStationName());
			session.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println("Could't fetch station with order " + order);
			System.out.println(e.toString());
		}finally{
			session.close();
		}
		return station;
	}
	
	private void saveStation(Station station){
		System.out.println("Spara station");
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.saveOrUpdate(station);
			session.getTransaction().commit();
			
		}catch(Exception e){
			// TODO: handle exception
			System.out.println( e.getMessage() );
		}finally{
			session.close();  
		}
	}

	
	private void savePatrol(PatrolImpl patrol){
		System.out.println("Spara patrull");
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.saveOrUpdate(patrol);
			session.getTransaction().commit();
			
		}catch(Exception e){
			// TODO: handle exception
			System.out.println( e.getMessage() );
		}finally{
			session.close();  
		}
	}

	private void saveTrack(Track track){
		System.out.println("Spara track");
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.saveOrUpdate(track);
			session.getTransaction().commit();
			
		}catch(Exception e){
			// TODO: handle exception
			System.out.println( e.getMessage() );
		}finally{
			session.close();  
		}
	}
	
	public PatrolImpl getPatrolById(int id){
		Session session = null;
		PatrolImpl patrol = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Query query = session.createQuery("from PatrolImpl patrol where patrol.patrolId=" + id);
			patrol = (PatrolImpl)query.list().get(0);
			System.out.println("Fetched patrol name: " + patrol.getPatrolName());
			session.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println("Could't fetch patrol with id " + id);
			System.out.println(e.toString());
		}finally{
			session.close();
		}
		return patrol;
	}
	
}
