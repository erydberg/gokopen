package se.gokopen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.StationDAO;
import se.gokopen.dao.StationNotFoundException;
import se.gokopen.dao.StationNotSavedException;
import se.gokopen.model.Station;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationDAO stationDao;

	@Override
	@Transactional
	public void saveStation(Station station) throws StationNotSavedException {
		stationDao.save(station);
	}

	@Override
	@Transactional
	public List<Station> getAllStations() {
		return stationDao.getAllStations();
	}

	@Override
	@Transactional
	public void deleteStation(Station station) throws StationNotFoundException {
		stationDao.delete(station);
	}

	@Override
	@Transactional
	public void deleteStationById(Integer id) throws StationNotFoundException {
		stationDao.deleteId(id);
	}

	@Override
	@Transactional
	public Station getStationById(Integer id) throws StationNotFoundException {
		return stationDao.getById(id);
	}

}
