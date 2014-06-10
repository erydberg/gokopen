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
		scoreDao.save(score);
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
}
