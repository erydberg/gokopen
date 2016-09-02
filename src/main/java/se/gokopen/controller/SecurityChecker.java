package se.gokopen.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import se.gokopen.model.Score;
import se.gokopen.model.Station;

public class SecurityChecker {

    public SecurityChecker(){
        
    }
    public static boolean isEditAllowedForCurrentUserOnStation(Station station){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
   
        return isUserAllowedToEdit(station.getStationUser(), user, name);
    }
    
    
      
    public static boolean isEditAllowedForCurrentUser(Score score) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        return isUserAllowedToEdit(score.getStation().getStationUser(), user, name);
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
    
    private static boolean isUserAllowedToEdit(String stationUser, User user, String name) {
        System.out.println("Security check: " + stationUser + " " + user + " " + name);
        if ((name.equalsIgnoreCase(stationUser))
                || (isUserAdmin(user) || (isUserStartFinshAdmin(user)))) {
            return true;
        } else {
            return false;
        }
    }
}
