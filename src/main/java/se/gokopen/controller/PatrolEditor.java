package se.gokopen.controller;

import java.beans.PropertyEditorSupport;

import se.gokopen.dao.PatrolNotFoundException;
import se.gokopen.model.PatrolImpl;
import se.gokopen.service.PatrolService;

public class PatrolEditor extends PropertyEditorSupport {


	private PatrolService patrolService;

	public PatrolEditor(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		PatrolImpl patrol = null;
		try {
			patrol = patrolService.getPatrolById(Integer.parseInt(text));
		} catch (PatrolNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setValue(patrol);
	}
	
	 @Override
	    public String getAsText() {
	        PatrolImpl patrol = (PatrolImpl) getValue();
	        if (patrol == null) {
	            return null;
	        } else {
//	            return patrol.getPatrolId().toString();
	        	return patrol.toString();
	        }
	    }
}




