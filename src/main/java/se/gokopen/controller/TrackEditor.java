package se.gokopen.controller;

import java.beans.PropertyEditorSupport;


import se.gokopen.dao.TrackNotFoundException;
import se.gokopen.model.Track;
import se.gokopen.service.TrackService;

public class TrackEditor extends PropertyEditorSupport {


    private TrackService trackService;

    public TrackEditor(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Track track = null;
        try {
            track = trackService.getTrackById(Integer.parseInt(text));
        } catch (TrackNotFoundException e) {
            e.printStackTrace();
        }
        setValue(track);
    }

    @Override
    public String getAsText() {
        Track track = (Track) getValue();
        if (track == null) {
            return null;
        } else {
            return track.getTrackId().toString();
        }
    }
}


