package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.StationNotFoundException;
import se.gokopen.dao.StationNotSavedException;
import se.gokopen.model.Station;

public interface StationService {
	public void saveStation(Station station) throws StationNotSavedException ;
	public List<Station> getAllStations();
	public void deleteStation(Station station) throws StationNotFoundException;
	public void deleteStationById(Integer id) throws StationNotFoundException;
	public Station getStationById(Integer id) throws StationNotFoundException;
}
