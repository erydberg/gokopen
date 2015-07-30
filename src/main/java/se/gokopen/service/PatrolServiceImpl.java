package se.gokopen.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.PatrolDAO;
import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.PatrolImpl;
import se.gokopen.model.ScoreImpl;
import se.gokopen.model.Track;

@Service
public class PatrolServiceImpl implements PatrolService {

	@Autowired
	private PatrolDAO patrolDao;
	
	@Override
	@Transactional
	public void savePatrol(PatrolImpl patrol) throws PatrolNotSavedException {
		patrolDao.save(patrol);
	}

	@Override
	@Transactional
	public List<PatrolImpl> getAllPatrols() {
		return patrolDao.getAllPatrols();
	}

	@Override
	@Transactional
	public void deletePatrol(PatrolImpl patrol) throws PatrolNotFoundException {
		patrolDao.delete(patrol);

	}

	@Override
	@Transactional
	public void deletePatrolById(Integer id) throws PatrolNotFoundException {
		patrolDao.deleteById(id);

	}

	@Override
	@Transactional
	public PatrolImpl getPatrolById(Integer id) throws PatrolNotFoundException {
		return patrolDao.getById(id);
	}
	
	

	@Override
	@Transactional
	public List<PatrolImpl> getAllPatrolsByTrackId(Integer trackId) {
		return patrolDao.getPatrolsByTrackId(trackId);
	}
	@Override
	@Transactional
	public List<PatrolImpl> getAllPatrolsByTrack(Track track) {
	    List<PatrolImpl> patrols = patrolDao.getPatrolsByTrack(track);
	    Collections.sort(patrols); //sorterar efter högst poäng (standardsortering för patrolsklassen)
		return patrols;
	}

	@Override
	@Transactional
	public List<PatrolImpl> getAllPatrolsLeftOnStation(Integer stationId) {
		List<PatrolImpl> allPatrols = patrolDao.getAllPatrols();
		
		Iterator<PatrolImpl> itt = allPatrols.iterator();
		while(itt.hasNext()){
			PatrolImpl patrol = (PatrolImpl) itt.next();
			Iterator<ScoreImpl> scores = patrol.getScores().iterator();
			while(scores.hasNext()){
				ScoreImpl score = scores.next();
				if(score.getStation().getStationId()==stationId){
					itt.remove();
					break;
				}
				
			}
			
		}

		return allPatrols;
	}

    @Override
    @Transactional
    public List<PatrolImpl> getAllPatrolsSortedByStatus() {
        return patrolDao.getAllPatrolsSortedByStatus();
    }

    @Override
    @Transactional
    public List<PatrolImpl> getAllPatrolsSortedByTroop() {
        return patrolDao.getAllPatrolsSortedByTroop();
    }

    @Override
    @Transactional
    public List<PatrolImpl> getAllPatrolsSortedByNumberOfStations() {
        List<PatrolImpl> patrols = patrolDao.getAllPatrols();
        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        return patrols;
    }

    @Override
    @Transactional
    public List<PatrolImpl> getAllPatrolsSortedByTrack() {
        List<PatrolImpl> patrols = patrolDao.getAllPatrolsSortedByTrack();
        return patrols;
    }

    @Override
    @Transactional
    public List<PatrolImpl> getAllPatrolsSortedByScore() {
        List<PatrolImpl> patrols = patrolDao.getAllPatrols();
        Collections.sort(patrols, Collections.reverseOrder(new BeanComparator("totalScore")));
        return patrols;
    }
    
    
}
