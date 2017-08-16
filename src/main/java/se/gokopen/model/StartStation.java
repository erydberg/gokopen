package se.gokopen.model;

import java.util.List;

public class StartStation {
	private Station station;
	private List<Patrol> patrols;
	
	
	
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public List<Patrol> getPatrols() {
		return patrols;
	}
	public void setPatrols(List<Patrol> patrols) {
		this.patrols = patrols;
	}
	
	

}
