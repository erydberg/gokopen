package se.gokopen.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.gokopen.model.Patrol;
import se.gokopen.model.Station;
import se.gokopen.model.Track;


@Repository
public class PatrolDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	public void save(Patrol patrol) throws PatrolNotSavedException{
		sessionFactory.getCurrentSession().saveOrUpdate(patrol);
	}

	public void delete(Patrol patrol) throws PatrolNotFoundException{
		sessionFactory.getCurrentSession().delete(patrol);
	}

	public void deleteById(Integer id) throws PatrolNotFoundException{
		Patrol patrol = getById(id);
		sessionFactory.getCurrentSession().delete(patrol);
	}

	@SuppressWarnings("unchecked")
	public Patrol getById(final Integer id) throws PatrolNotFoundException{
		Patrol patrol = null;
		
		List<Patrol> patrols = (List<Patrol>) sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.patrolId= :patrid").setParameter("patrid", id).list();
		if (patrols==null || patrols.isEmpty() || patrols.size()>1){
			throw new PatrolNotFoundException("Hittar inte patrullen med id: " + id);
		}
		patrol = patrols.get(0);
		return patrol;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getAllPatrols(){
	    List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol as ptr order by ptr.patrolName asc").setCacheable(true).list();
		return patrols;
//	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Patrol.class);
//      criteria.addOrder(Order.asc("patrolName"));
//      return (List<Patrol>) new LinkedHashSet<Patrol>(criteria.list());
//		 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Patrol.class);
//	        criteria.setFetchMode("track", FetchMode.JOIN);
//	        criteria.setFetchMode("scores", FetchMode.JOIN);
//	        criteria.addOrder(Order.asc("patrolName"));
//	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//	        criteria.setCacheable(true);
//	        List<Patrol> patrols = criteria.list();
//	        return patrols;
	       
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getAllPatrolsCriteria(){
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Patrol.class);
        criteria.setFetchMode("track", FetchMode.JOIN);
        criteria.setFetchMode("scores", FetchMode.JOIN);
        criteria.addOrder(Order.asc("patrolName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setCacheable(true);
        List<Patrol> patrols = criteria.list();
        return patrols;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getPatrolsByTrackId(Integer trackId){
		List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.fk_track= :trackid order by patr.patrolName asc").setParameter("trackid",trackId).list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getPatrolsByTrack(Track track){
		List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.track= :track order by patr.patrolName asc").setParameter("track",track).list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getAllPatrolsSortedByStatus(){
	    List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr order by patr.status asc").list();
	    return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getAllPatrolsSortedByTroop(){
	    List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr order by patr.troop asc").list();
        return patrols;
	}
	
	@SuppressWarnings("unchecked")
    public List<Patrol> getAllPatrolsSortedByTrack() {
        List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr order by patr.track asc").list();
        return patrols;
    }

	@SuppressWarnings("unchecked")
	public List<Patrol> getAllPatrolsByStartStation(Station station) {
		List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.startStation= :station order by patr.patrolName asc").setParameter("station", station).list();
		return patrols;
	}
}

