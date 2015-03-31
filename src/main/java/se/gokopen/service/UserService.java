package se.gokopen.service;

import java.util.List;

import se.gokopen.dao.UserNotFoundException;
import se.gokopen.model.User;

public interface UserService {
    public User getUserById(Integer id) throws UserNotFoundException;
    public User getUser(String username) throws UserNotFoundException;
    public void deleteUser(User user);
    public void deleteUserByUsername(String username) throws UserNotFoundException;
    public void saveUser(User user);
    public List<User> getAllUsers();
    public void deleteUserById(int id) throws UserNotFoundException;
}
