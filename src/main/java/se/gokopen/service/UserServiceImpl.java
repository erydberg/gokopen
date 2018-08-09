package se.gokopen.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.UserDao;
import se.gokopen.dao.UserNotFoundException;
import se.gokopen.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    @Transactional
    public User getUser(String username) throws UserNotFoundException {
        return userDao.getUserByName(username);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);

    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
            User delUser = getUser(username);
            userDao.deleteUser(delUser);
        

    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public User getUserById(Integer id) throws UserNotFoundException {
        return userDao.getUserById(id);

    }

    @Override
    @Transactional
    public void deleteUserById(int id) throws UserNotFoundException {
        User user = userDao.getUserById(id);
        deleteUser(user);
    }
}
