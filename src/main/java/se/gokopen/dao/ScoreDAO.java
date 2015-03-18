package se.gokopen.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;

@Repository
@Transactional
public class ScoreDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void save (ScoreImpl score) throws ScoreNotSavedException{
		sessionFactory.getCurrentSession().saveOrUpdate(score);
	}
 
	@SuppressWarnings("unchecked")
	public ScoreImpl getById(Integer id) throws ScoreNotFoundException{
		ScoreImpl score = null;
		
		List<ScoreImpl> scores = (List<ScoreImpl>) sessionFactory.getCurrentSession().createQuery("from ScoreImpl score where score.scoreId=?").setParameter(0, id).list();
		if (scores==null || scores.isEmpty() || scores.size()>1){
			throw new ScoreNotFoundException("Hittar inte poäng med id: " + id);
		}
		score = scores.get(0);
		return score;
	}
	
	public void delete (ScoreImpl score) throws ScoreNotFoundException{
		sessionFactory.getCurrentSession().delete(score);
	}
	
	public void deleteById(Integer id) throws ScoreNotFoundException{
		ScoreImpl score = getById(id);
		delete(score);
	}
	
	@SuppressWarnings("unchecked")
	public List<ScoreImpl> getAllScores(){
		List<ScoreImpl> scores = sessionFactory.getCurrentSession().createQuery("from ScoreImpl").list();
		return scores;
	}
	
	@SuppressWarnings("unchecked")
	public List<ScoreImpl> getAllScoresByPatrol(PatrolImpl patrol){
		List<ScoreImpl> scores = sessionFactory.getCurrentSession().createQuery("from ScoreImpl as score where score.fk_patrol=? order by score.fk_station").setParameter(0, patrol.getPatrolId()).list();
		return scores;
	}
	
	@SuppressWarnings("unchecked")
	public List<ScoreImpl> getAllScoresByPatrolId(Integer id){
		List<ScoreImpl> scores = sessionFactory.getCurrentSession().createQuery("from ScoreImpl as score where score.fk_patrol=? order by score.fk_station").setParameter(0, id).list();
		return scores;
	}
	
	@SuppressWarnings("unchecked")
    public ScoreImpl getScoreForPatrolOnStation(Integer patrolId, Integer stationId) throws ScoreNotFoundException{
	    List<ScoreImpl> scores = sessionFactory.getCurrentSession().createQuery("from ScoreImpl as score where score.patrol.patrolId= :patrolid and score.station.stationId=:stationid").setParameter("patrolid", patrolId).setParameter("stationid",stationId).list();
	    if(scores==null||scores.isEmpty()){
	        throw new ScoreNotFoundException("Hittar ingen sparad poäng för denna patrull på denna kontroll");
	    }
	    return scores.get(0);
	}
}
