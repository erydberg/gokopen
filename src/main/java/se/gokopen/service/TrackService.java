package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.dao.TrackNotSavedException;
import se.gokopen.model.Track;



public interface TrackService {
	public void saveTrack(Track track) throws TrackNotSavedException;
	public List<Track> getAllTracks();
	public void deleteTrack(Track track) throws TrackNotFoundException;
	public void deleteTrackById(Integer id) throws TrackNotFoundException;
	public Track getTrackById(Integer id) throws TrackNotFoundException;
}
