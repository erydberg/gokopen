package se.gokopen.hibernate;


//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Station;
import se.gokopen.model.Track;




public class CreateTables {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
	
//		AnnotationConfiguration config  = new AnnotationConfiguration();
	Configuration config  = new Configuration();
	config.addAnnotatedClass(Station.class);
	config.addAnnotatedClass(Track.class);
	config.addAnnotatedClass(ScoreImpl.class);
	config.addAnnotatedClass(PatrolImpl.class);
		

		config.configure("/se/gokopen/hibernate/mysqlserver.cfg.xml");
//		config.configure("/se/gokopen/hibernate/mysql.cfg.xml");
		new SchemaExport(config).create(true, true);  //Create tables
		
	
		System.out.println("Created tables");
		
	}

}
