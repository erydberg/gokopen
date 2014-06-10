package se.gokopen.dao;

public class PatrolNotSavedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6060793425112771875L;

	private String errorMsg;
	
	public PatrolNotSavedException(String msg){
		this.errorMsg = msg;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
}
