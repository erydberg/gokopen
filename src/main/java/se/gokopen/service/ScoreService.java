package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.model.ScoreImpl;


public interface ScoreService {
	public void saveScore(ScoreImpl score) throws ScoreNotSavedException;
	public List<ScoreImpl> getAllScores();
	public List<ScoreImpl> getScoreByPatrolId(Integer id);
	public void deleteScore(ScoreImpl score) throws ScoreNotFoundException;
	public void deleteScoreById(Integer id) throws ScoreNotFoundException;
	public ScoreImpl getScoreById(Integer id) throws ScoreNotFoundException;
}
