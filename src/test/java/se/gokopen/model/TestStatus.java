package se.gokopen.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestStatus {
    
    @Test
    public void shouldReturnAllPossibleStatus(){
        assertEquals(Status.values().length,(4));
    }

}
