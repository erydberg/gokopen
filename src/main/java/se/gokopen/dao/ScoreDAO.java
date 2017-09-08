package se.gokopen.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.model.Patrol;
import se.gokopen.model.Score;

@Repository
@Transactional
public class ScoreDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void save (Score score) throws ScoreNotSavedException{
		sessionFactory.getCurrentSession().saveOrUpdate(score);
	}
 
	@SuppressWarnings("unchecked")
	public Score getById(Integer id) throws ScoreNotFoundException{
		Score score = null;
		
		List<Score> scores = (List<Score>) sessionFactory.getCurrentSession().createQuery("from Score score where score.scoreId=?").setParameter(0, id).list();
		if (scores==null || scores.isEmpty() || scores.size()>1){
			throw new ScoreNotFoundException("Hittar inte poäng med id: " + id);
		}
		score = scores.get(0);
		return score;
	}
	
	public void delete (Score score) throws ScoreNotFoundException{
		sessionFactory.getCurrentSession().delete(score);
	}
	
	public void deleteById(Integer id) throws ScoreNotFoundException{
		Score score = getById(id);
		delete(score);
	}
	
	@SuppressWarnings("unchecked")
	public List<Score> getAllScores(){
		List<Score> scores = sessionFactory.getCurrentSession().createQuery("from Score").list();
		return scores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Score> getAllScoresByPatrol(Patrol patrol){
		List<Score> scores = sessionFactory.getCurrentSession().createQuery("from Score as score where score.fk_patrol=? order by score.fk_station").setParameter(0, patrol.getPatrolId()).list();
		return scores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Score> getAllScoresByPatrolId(Integer id){
		List<Score> scores = sessionFactory.getCurrentSession().createQuery("from Score as score where score.fk_patrol=? order by score.fk_station").setParameter(0, id).list();
		return scores;
	}
	
	@SuppressWarnings("unchecked")
    public Score getScoreForPatrolOnStation(Integer patrolId, Integer stationId) throws ScoreNotFoundException{
	    List<Score> scores = sessionFactory.getCurrentSession().createQuery("from Score as score where score.patrol.patrolId= :patrolid and score.station.stationId=:stationid").setParameter("patrolid", patrolId).setParameter("stationid",stationId).list();
	    if(scores==null||scores.isEmpty()){
	        throw new ScoreNotFoundException("Hittar ingen sparad poäng för denna patrull på denna kontroll");
	    }
	    return scores.get(0);
	}

	@SuppressWarnings("unchecked")
    public List<Score> getAllScoresOnStation(Integer stationId) {
	    return sessionFactory.getCurrentSession().createQuery("from Score as score where score.station.stationId= :stationid order by score.lastSaved desc").setParameter("stationid", stationId).list();
    }
}
