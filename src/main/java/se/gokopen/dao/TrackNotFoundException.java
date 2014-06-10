package se.gokopen.dao;

public class TrackNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 708200822084661105L;
	private String errorMsg;
	
	public TrackNotFoundException(String msg){
		errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}
