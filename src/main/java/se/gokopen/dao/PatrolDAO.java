package se.gokopen.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.Track;

@Repository
public class PatrolDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	public void save(PatrolImpl patrol) throws PatrolNotSavedException{
		sessionFactory.getCurrentSession().saveOrUpdate(patrol);
	}

	public void delete(PatrolImpl patrol) throws PatrolNotFoundException{
		sessionFactory.getCurrentSession().delete(patrol);
	}

	public void deleteById(Integer id) throws PatrolNotFoundException{
		PatrolImpl patrol = getById(id);
		sessionFactory.getCurrentSession().delete(patrol);
	}

	@SuppressWarnings("unchecked")
	public PatrolImpl getById(final Integer id) throws PatrolNotFoundException{
		PatrolImpl patrol = null;
		
		List<PatrolImpl> patrols = (List<PatrolImpl>) sessionFactory.getCurrentSession().createQuery("from PatrolImpl patr where patr.patrolId=?").setParameter(0, id).list();
		if (patrols==null || patrols.isEmpty() || patrols.size()>1){
			throw new PatrolNotFoundException("Hittar inte patrullen med id: " + id);
		}
		patrol = patrols.get(0);
		return patrol;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatrolImpl> getAllPatrols(){
		List<PatrolImpl> patrols = sessionFactory.getCurrentSession().createQuery("from PatrolImpl as ptr order by ptr.patrolName asc").list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatrolImpl> getPatrolsByTrackId(Integer trackId){
		List<PatrolImpl> patrols = sessionFactory.getCurrentSession().createQuery("from PatrolImpl patr where patr.fk_track=? order by patr.patrolName asc").setParameter(0,trackId).list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatrolImpl> getPatrolsByTrack(Track track){
		List<PatrolImpl> patrols = sessionFactory.getCurrentSession().createQuery("from PatrolImpl patr where patr.track=? order by patr.patrolName asc").setParameter(0,track).list();
		return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatrolImpl> getAllPatrolsSortedByStatus(){
	    List<PatrolImpl> patrols = sessionFactory.getCurrentSession().createQuery("from PatrolImpl patr order by patr.status asc").list();
	    return patrols;
	}
	
	@SuppressWarnings("unchecked")
	public List<PatrolImpl> getAllPatrolsSortedByTroop(){
	    List<PatrolImpl> patrols = sessionFactory.getCurrentSession().createQuery("from PatrolImpl patr order by patr.troop asc").list();
        return patrols;
	}
	
	@SuppressWarnings("unchecked")
    public List<PatrolImpl> getAllPatrolsSortedByTrack() {
        List<PatrolImpl> patrols = sessionFactory.getCurrentSession().createQuery("from PatrolImpl patr order by patr.track asc").list();
        return patrols;
    }
}

