package se.gokopen.dao;

public class ScoreNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1441460385287017257L;

	private String errorMsg;
	
	public ScoreNotFoundException(String msg){
		errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}
