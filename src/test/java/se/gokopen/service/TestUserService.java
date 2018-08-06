package se.gokopen.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})

public class TestUserService {

    
    @Autowired
    private UserService userService;
    
    @Ignore
    @Test
    public void shouldCreateUserWithAdminRights(){
       User user = new User();
       user.setUsername("testuser");
       user.setRole("ROLE_ADMIN");
       user.setPassword("testuser");
       user.setEnabled(true);
       
       userService.saveUser(user);
       
       assertNotNull(user.getId());
       
       userService.deleteUser(user);
    }
}
