package se.gokopen.dao;

public class ScoreNotSavedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7341789702088704170L;

	private String errorMsg;
	
	public ScoreNotSavedException(String msg){
		errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
}
