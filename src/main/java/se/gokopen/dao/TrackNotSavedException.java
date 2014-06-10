package se.gokopen.dao;

public class TrackNotSavedException extends Exception {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -3819178369676667490L;
	private String errorMsg;
	
	public TrackNotSavedException(String msg){
		errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}
