package se.gokopen.controller;

import java.beans.PropertyEditorSupport;
import se.gokopen.dao.StationNotFoundException;
import se.gokopen.model.Station;
import se.gokopen.service.StationService;

public class StationEditor extends PropertyEditorSupport {


	private StationService stationService;

	public StationEditor(StationService stationService) {
        this.stationService = stationService;
    }

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Station station = null;
		try {
			station = stationService.getStationById(Integer.parseInt(text));
		} catch (StationNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setValue(station);
	}
	
	 @Override
	    public String getAsText() {
	        Station station = (Station) getValue();
	        if (station == null) {
	            return null;
	        } else {
	            return station.getStationId().toString();
	        }
	    }
}


