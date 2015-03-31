package se.gokopen.dao;

import java.util.List;

import se.gokopen.dao.UserNotFoundException;
import se.gokopen.model.User;

public interface UserDao {
    public void saveUser(User user);
    public void deleteUser(User user);
    public User getUserByName(String username) throws UserNotFoundException;
    public List<User> getAllUsers();
    public User getUserById(Integer id) throws UserNotFoundException;
    

}
