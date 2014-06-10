package se.gokopen.hibernate;



import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Station;
import se.gokopen.model.Track;



public class HibernateUtil {

//	private static Logger logger = Logger.getLogger("se.goteborg.livsmedelslokal.hibernate");
	
	private static final SessionFactory sessionFactory;
	
	    static {
	        try {
	        	System.out.println("Drar igång");
//	        	logger.log(Level.CONFIG,"Create Sessionfactory, load classes.");	
	            sessionFactory = new Configuration()	    
	            	.addAnnotatedClass(PatrolImpl.class)
	            	.addAnnotatedClass(ScoreImpl.class)
	            	.addAnnotatedClass(Station.class)
	            	.addAnnotatedClass(Track.class)
	            	.configure("/se/gokopen/hibernate/mysql.cfg.xml").buildSessionFactory();   //lokal utv
	
	        } catch (Throwable ex) {
	            // exception!
	        	ex.printStackTrace();
//	        	logger.log(Level.WARNING, "Couldn't create Sessionfactory...");
	      	
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static Session getSession()
	            throws HibernateException {
//	    	logger.log(Level.FINE,"Returnerar en session.");
	    	return sessionFactory.openSession();
	    }
	}
