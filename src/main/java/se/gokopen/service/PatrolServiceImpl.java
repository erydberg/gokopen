package se.gokopen.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.PatrolDAO;
import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Score;
import se.gokopen.model.Station;
import se.gokopen.model.Status;
import se.gokopen.model.Track;

@Service
public class PatrolServiceImpl implements PatrolService {

    @Autowired
    private PatrolDAO patrolDao;

    @Override
    @Transactional
    public void savePatrol(Patrol patrol) throws PatrolNotSavedException {
        if(isNewPatrol(patrol)) {
            Date registered = new Date();
            patrol.setDateRegistered(registered);
        }
        patrolDao.save(patrol);
    }

    private boolean isNewPatrol(Patrol patrol) {
        if(patrol.getPatrolId()==null || patrol.getPatrolId()==0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrols() {
        return patrolDao.getAllPatrols();
    }

    @Override
    @Transactional
    public void deletePatrol(Patrol patrol) throws PatrolNotFoundException {
        patrolDao.delete(patrol);

    }

    @Override
    @Transactional
    public void deletePatrolById(Integer id) throws PatrolNotFoundException {
        patrolDao.deleteById(id);

    }

    @Override
    @Transactional
    public Patrol getPatrolById(Integer id) throws PatrolNotFoundException {
        return patrolDao.getById(id);
    }


    @Override
    @Transactional
    public List<Patrol> getAllPatrolsByTrackId(Integer trackId) {
        return patrolDao.getPatrolsByTrackId(trackId);
    }
    @Override
    @Transactional
    public List<Patrol> getAllPatrolsByTrack(Track track) {
        List<Patrol> patrols = patrolDao.getPatrolsByTrack(track);
        Collections.sort(patrols); //sorterar efter högst poäng (standardsortering för patrolsklassen)
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsLeftOnStation(Integer stationId) {
        List<Patrol> allPatrols = patrolDao.getAllPatrols();

        Iterator<Patrol> itt = allPatrols.iterator();
        while(itt.hasNext()){
            Patrol patrol = (Patrol) itt.next();
            Iterator<Score> scores = patrol.getScores().iterator();
            while(scores.hasNext()){
                Score score = scores.next();
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
    public List<Patrol> getAllPatrolsSortedByStatus() {
        return patrolDao.getAllPatrolsSortedByStatus();
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByTroop() {
        return patrolDao.getAllPatrolsSortedByTroop();
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByNumberOfStations() {
        List<Patrol> patrols = patrolDao.getAllPatrols();
        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByTrack() {
        List<Patrol> patrols = patrolDao.getAllPatrolsSortedByTrack();
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByScore() {
        List<Patrol> patrols = patrolDao.getAllPatrols();
        Collections.sort(patrols, Collections.reverseOrder(new BeanComparator("totalScore")));
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllActivePatrolsLeftOnStation(Integer stationId) {
        List<Patrol> patrols = getAllPatrolsLeftOnStation(stationId);
        List<Patrol> activePatrols = getActiveAndWaitingPatolsFromList(patrols);
        return activePatrols;
    }

    public List<Patrol> getActiveAndWaitingPatolsFromList(List<Patrol> patrols) {
        List<Patrol> onlyActivePatrols = new ArrayList<Patrol>();
        for(Patrol patrol:patrols){
            if(patrol.getStatus()!=null && (patrol.getStatus().equals(Status.REGISTERED) || patrol.getStatus().equals(Status.ACTIVE) || patrol.getStatus().equals(Status.FINISHED) )){
                onlyActivePatrols.add(patrol);
            }
        }
        return onlyActivePatrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsCriteria() {
        return patrolDao.getAllPatrols();
    }

    @Override
    @Transactional
    public void saveAllpatrols(List<Patrol> patrols) throws PatrolNotSavedException {
        for(Patrol patrol:patrols){
            this.savePatrol(patrol);
        }

    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsByStartStation(Station station) {
        return patrolDao.getAllPatrolsByStartStation(station);
    }
}
