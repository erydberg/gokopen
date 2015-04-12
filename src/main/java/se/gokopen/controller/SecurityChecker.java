package se.gokopen.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import se.gokopen.model.ScoreImpl;

public class SecurityChecker {

    public SecurityChecker(){
        
    }
    
    public static boolean isEditAllowedForCurrentUser(ScoreImpl score) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        if ((name.equalsIgnoreCase(score.getStation().getStationUser()))
                || (isUserAdmin(user) || (isUserStartFinshAdmin(user)))) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isUserAdmin(User user){
        for(GrantedAuthority authority : user.getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isUserStartFinshAdmin(User user){
        for(GrantedAuthority authority : user.getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase("ROLE_STARTFINISH")){
                return true;
            }
        }
        return false;
    }
}
