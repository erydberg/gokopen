package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.Patrol;
import se.gokopen.model.Station;
import se.gokopen.model.Track;

public interface PatrolService {
	public void savePatrol(Patrol patrol) throws PatrolNotSavedException;
	public void saveAllpatrols(List<Patrol> patrols) throws PatrolNotSavedException;
	public List<Patrol> getAllPatrols();
	public void deletePatrol(Patrol patrol) throws PatrolNotFoundException;
	public void deletePatrolById(Integer id) throws PatrolNotFoundException;
	public Patrol getPatrolById(Integer id) throws PatrolNotFoundException;
	public List<Patrol> getAllPatrolsByTrackId(Integer trackId);
	public List<Patrol> getAllPatrolsByTrack(Track track);
	public List<Patrol> getAllPatrolsLeftOnStation(Integer stationId);
	public List<Patrol> getAllPatrolsSortedByStatus();
	public List<Patrol> getAllPatrolsSortedByTroop();
	public List<Patrol> getAllPatrolsSortedByNumberOfStations();
    public List<Patrol> getAllPatrolsSortedByTrack();
    public List<Patrol> getAllPatrolsSortedByScore();
    public List<Patrol> getAllActivePatrolsLeftOnStation(Integer stationId);
    public List<Patrol> getActiveAndWaitingPatolsFromList(List<Patrol> patrols);
    public List<Patrol> getAllPatrolsCriteria();
	public List<Patrol> getAllPatrolsByStartStation(Station station);
}

