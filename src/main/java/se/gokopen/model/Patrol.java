package se.gokopen.model;

import java.util.Set;

public interface Patrol {
	void addScore(); 
	void updateScore();
	ScoreImpl getScore();
	Set<ScoreImpl> getScores();
	Integer getPatrolId();
	void deleteScore(ScoreImpl score);
}
