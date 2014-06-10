package se.gokopen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.TrackDAO;
import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.dao.TrackNotSavedException;
import se.gokopen.model.Track;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackDAO trackDao;
	
	@Override
	@Transactional
	public void saveTrack(Track track) throws TrackNotSavedException {
		trackDao.save(track);
	}

	@Override
	@Transactional
	public List<Track> getAllTracks() {
		return trackDao.getAllTracks();
	}

	@Override
	@Transactional
	public void deleteTrack(Track track) throws TrackNotFoundException {
		trackDao.delete(track);
	}

	@Override
	@Transactional
	public void deleteTrackById(Integer id) throws TrackNotFoundException {
		trackDao.deleteId(id);
	}

	@Override
	@Transactional
	public Track getTrackById(Integer id) throws TrackNotFoundException {
		return trackDao.getById(id);
	}

}
