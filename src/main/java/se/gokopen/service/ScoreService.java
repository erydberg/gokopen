package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.ScoreNotFoundException;
import se.gokopen.dao.ScoreNotSavedException;
import se.gokopen.model.Score;


public interface ScoreService {
	public void saveScore(Score score) throws ScoreNotSavedException;
	public List<Score> getAllScores();
	public List<Score> getScoreByPatrolId(Integer id);
	public List<Score> getScoreOnStation(Integer stationId);
	public void deleteScore(Score score) throws ScoreNotFoundException;
	public void deleteScoreById(Integer id) throws ScoreNotFoundException;
	public Score getScoreById(Integer id) throws ScoreNotFoundException;
}
