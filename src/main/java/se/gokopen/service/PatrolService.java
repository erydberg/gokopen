package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.dao.PatrolNotSavedException;
import se.gokopen.model.PatrolImpl;
import se.gokopen.model.Track;

public interface PatrolService {
	public void savePatrol(PatrolImpl patrol) throws PatrolNotSavedException;
	public List<PatrolImpl> getAllPatrols();
	public void deletePatrol(PatrolImpl patrol) throws PatrolNotFoundException;
	public void deletePatrolById(Integer id) throws PatrolNotFoundException;
	public PatrolImpl getPatrolById(Integer id) throws PatrolNotFoundException;
	public List<PatrolImpl> getAllPatrolsByTrackId(Integer trackId);
	public List<PatrolImpl> getAllPatrolsByTrack(Track track);
	public List<PatrolImpl> getAllPatrolsLeftOnStation(Integer stationId);
}
