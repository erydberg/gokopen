package se.gokopen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.ScoreDAO;
import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.model.ScoreImpl;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDAO scoreDao;

    @Override
    @Transactional
    public void saveScore(ScoreImpl score) throws ScoreNotSavedException {
        if(isScoreInEditMode(score) || !hasScoreBeenSavedBefore(score)){
            scoreDao.save(score);
        }
        else{
            throw new ScoreNotSavedException("Det finns redan poäng registrerat för denna patrull på denna kontroll.");
        }
    }

    @Override
    @Transactional
    public List<ScoreImpl> getAllScores() {
        return scoreDao.getAllScores();
    }

    @Override
    @Transactional
    public List<ScoreImpl> getScoreByPatrolId(Integer id) {
        return scoreDao.getAllScoresByPatrolId(id);
    }

    @Override
    @Transactional
    public void deleteScore(ScoreImpl score) throws ScoreNotFoundException {
        scoreDao.delete(score);
    }

    @Override
    @Transactional
    public void deleteScoreById(Integer id) throws ScoreNotFoundException {
        scoreDao.deleteById(id);
    }

    @Override
    @Transactional
    public ScoreImpl getScoreById(Integer id) throws ScoreNotFoundException {
        return scoreDao.getById(id);
    }

    
    private boolean hasScoreBeenSavedBefore(ScoreImpl score){
        Integer patrolId = score.getPatrol().getPatrolId();
        Integer stationId = score.getStation().getStationId();
        try{
            ScoreImpl prevscore = scoreDao.getScoreForPatrolOnStation(patrolId, stationId);   
            System.out.println("Hittar poäng (" + prevscore.getScorePoint() + ") för patrullid: " + patrolId + " för kontroll " + stationId);
        }catch(ScoreNotFoundException e){
            System.out.println("Hittar inget poäng, ok att spara");
            return false;
        }
        
        return true;
    }
    
    private boolean isScoreInEditMode(ScoreImpl score){
        if(score.getScoreId()==null || score.getScoreId()==0){
            return false;
        }else{
            return true;
        }
    }
}
