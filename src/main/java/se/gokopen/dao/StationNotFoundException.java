package se.gokopen.dao;

public class StationNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5175652910905978697L;
	private String errorMsg;
	
	public StationNotFoundException(String msg){
		errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}
