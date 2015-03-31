package se.gokopen.dao;

public class UserNotFoundException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3020745484369885478L;
    private String errorMsg;

    public UserNotFoundException(String msg){
        this.errorMsg = msg;
    }

    public String getErrorMsg(){
        return errorMsg;
    }

}
