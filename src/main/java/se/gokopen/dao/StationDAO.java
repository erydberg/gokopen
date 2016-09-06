package se.gokopen.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.gokopen.model.Station;

@Repository
public class StationDAO {

	@Autowired
    private SessionFactory sessionFactory;
 
	public void save (Station station) throws StationNotSavedException{
		sessionFactory.getCurrentSession().saveOrUpdate(station);
	}
	
	public void delete (Station station) throws StationNotFoundException{
		sessionFactory.getCurrentSession().delete(station);
	}
	
	public void deleteId (Integer id) throws StationNotFoundException{
		Station station = getById(id);
		delete(station);
	}
	
	@SuppressWarnings("unchecked")
	public Station getById(Integer id) throws StationNotFoundException{
		Station station = null;
		
		List<Station> stations = (List<Station>) sessionFactory.getCurrentSession().createQuery("from Station station where station.stationId= :stationid").setParameter("stationid", id).list();
		if (stations==null || stations.isEmpty() || stations.size()>1){
			throw new StationNotFoundException("Hittar inte kontrollen med id: " + id);
		}
		station = stations.get(0);
		return station;
	}
	
	@SuppressWarnings("unchecked")
	public List<Station> getAllStations(){
		List<Station> stations = sessionFactory.getCurrentSession().createQuery("from Station as station order by station.stationNumber asc").list();
		return stations;
	}

}
