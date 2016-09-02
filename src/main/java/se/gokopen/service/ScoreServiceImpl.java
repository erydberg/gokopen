package se.gokopen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.ScoreDAO;
import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.model.Score;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDAO scoreDao;

    @Override
    @Transactional
    public void saveScore(Score score) throws ScoreNotSavedException {
        if(isScoreInEditMode(score) || !hasScoreBeenSavedBefore(score)){
            Date saved = new Date();
            score.setLastSaved(saved);
            scoreDao.save(score);

        }
        else{
            throw new ScoreNotSavedException("Det finns redan poäng registrerat för denna patrull på denna kontroll.");
        }
    }

    @Override
    @Transactional
    //TODO ta bort=
    public List<Score> getAllScores() {
        return scoreDao.getAllScores();
    }

    @Override
    @Transactional
    public List<Score> getScoreByPatrolId(Integer id) {
        return scoreDao.getAllScoresByPatrolId(id);
    }

    @Override
    @Transactional
    public void deleteScore(Score score) throws ScoreNotFoundException {
        scoreDao.delete(score);
    }

    @Override
    @Transactional
    public void deleteScoreById(Integer id) throws ScoreNotFoundException {
        scoreDao.deleteById(id);
    }

    @Override
    @Transactional
    public Score getScoreById(Integer id) throws ScoreNotFoundException {
        return scoreDao.getById(id);
    }

    
    private boolean hasScoreBeenSavedBefore(Score score){
        Integer patrolId = score.getPatrol().getPatrolId();
        Integer stationId = score.getStation().getStationId();
        try{
            Score prevscore = scoreDao.getScoreForPatrolOnStation(patrolId, stationId);   
        }catch(ScoreNotFoundException e){
            System.out.println("Hittar inget poäng, ok att spara");
            return false;
        }
        
        return true;
    }
    
    private boolean isScoreInEditMode(Score score){
        if(score.getScoreId()==null || score.getScoreId()==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<Score> getScoreOnStation(Integer stationId) {
        List<Score> scores = scoreDao.getAllScoresOnStation(stationId);
        return scores;
    }
}
