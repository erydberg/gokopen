package se.gokopen.dao;

public class PatrolNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8584794716979986278L;

	private String errorMsg;
	
	public PatrolNotFoundException(String msg){
		this.errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}
