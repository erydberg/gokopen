package se.gokopen.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Station;
import se.gokopen.model.Track;



public class HibernateUtil {

//	private static Logger logger = Logger.getLogger("se.goteborg.livsmedelslokal.hibernate");
	
	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	    static {
	        try {
	        	System.out.println("Drar igång");
//	        	logger.log(Level.CONFIG,"Create Sessionfactory, load classes.");
	        	Configuration configuration = new Configuration().addAnnotatedClass(PatrolImpl.class)
	            	.addAnnotatedClass(ScoreImpl.class)
	            	.addAnnotatedClass(Station.class)
	            	.addAnnotatedClass(Track.class)
	            	.configure("/se/gokopen/hibernate/mysql.cfg.xml");   //lokal utv
	            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	                configuration.getProperties()).build();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            
	            
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
