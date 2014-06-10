package se.gokopen.dao;

public class StationNotSavedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6986239963918137924L;
	private String errorMsg;
	
	public StationNotSavedException (String msg){
		errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}

}
