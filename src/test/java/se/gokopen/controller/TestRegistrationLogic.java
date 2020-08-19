package se.gokopen.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import se.gokopen.model.ConfigRegistration;

public class TestRegistrationLogic {
    private ConfigRegistration config;

    @Before
    public void init(){
        config = new ConfigRegistration();
    }
    
    @Test
    public void shouldReturnTrueIfAllParametersAreGood() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(yesterday());
        config.setLastRegisterDay(tomorrow());
        config.setMaxPatrols(200);
        int noOfPatrols = 100;
        
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    
    @Test
    public void shouldReturnFalseSinceRegistrationIsOff() {
        config.setAllowPublicRegistration(false);
        config.setFirstRegisterDay(yesterday());
        config.setLastRegisterDay(tomorrow());
        int noOfPatrols = 100;
        
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnFalseSinceDateIsHistory() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(yesterday());
        config.setLastRegisterDay(yesterday()); 
        int noOfPatrols = 100;
        
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnTrueSinceEndDateIsTomorrow() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(yesterday());
        config.setLastRegisterDay(tomorrow()); 
        config.setMaxPatrols(200);
        int noOfPatrols = 100;
        
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }

    @Test
    public void shouldReturnFalseSinceStartDateIsTomorrow() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(tomorrow());
        config.setLastRegisterDay(tomorrow());
        config.setMaxPatrols(200);
        int noOfPatrols = 100;

        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }

    @Test
    public void shouldReturnFalseSinceFull() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(yesterday());
        config.setLastRegisterDay(tomorrow()); 
        config.setMaxPatrols(200);
        int noOfPatrols = 200;
        
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnTrueSinceNotFull() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(yesterday());
        config.setLastRegisterDay(tomorrow()); 
        config.setMaxPatrols(300);
        int noOfPatrols = 200;
        
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnTrueSinceStartsToday() {
        config.setAllowPublicRegistration(true);
        config.setFirstRegisterDay(new Date());
        config.setLastRegisterDay(tomorrow());
        int noOfPatrols = 200;
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnFalseSinceNoAllowIsSet() {
        int noOfPatrols = 200;
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    
    private Date tomorrow() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
