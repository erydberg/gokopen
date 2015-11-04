package se.gokopen.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.gokopen.model.Patrol;
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
		
		List<Patrol> patrols = (List<Patrol>) sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.patrolId=?").setParameter(0, id).list();
		if (patrols==null || patrols.isEmpty() || patrols.size()>1){
			throw new PatrolNotFoundException("Hittar inte patrullen med id: " + id);
		}
		patrol = patrols.get(0);
		return patrol;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getAllPatrols(){
		List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol as ptr order by ptr.patrolName asc").list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getPatrolsByTrackId(Integer trackId){
		List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.fk_track=? order by patr.patrolName asc").setParameter(0,trackId).list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<Patrol> getPatrolsByTrack(Track track){
		List<Patrol> patrols = sessionFactory.getCurrentSession().createQuery("from Patrol patr where patr.track=? order by patr.patrolName asc").setParameter(0,track).list();
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
}

