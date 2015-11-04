package se.gokopen.controller;

import java.beans.PropertyEditorSupport;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.model.Patrol;
import se.gokopen.service.PatrolService;

public class PatrolEditor extends PropertyEditorSupport {


    private PatrolService patrolService;

    public PatrolEditor(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(text));
        } catch (PatrolNotFoundException e) {
            e.printStackTrace();
        }
        setValue(patrol);
    }

    @Override
    public String getAsText() {
        Patrol patrol = (Patrol) getValue();
        if (patrol == null) {
            return null;
        } else {
            return patrol.toString();
        }
    }
}




