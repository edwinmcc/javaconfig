package edu.java.web.service;

import edu.java.web.beans.User;
import edu.java.web.dao.UserDAO;
import edu.java.web.status.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by edwin on 22/01/17.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "userDAO")
    private UserDAO userDAO;

    public OperationResult getAllUsers() {
        return userDAO.getAllUsers();
    }

    public OperationResult getUser(String userName) {
        return userDAO.getUser(userName);
    }

    public OperationResult addUser(User user) {
        return userDAO.addUser(user);
    }

    public OperationResult editUser(User user) {
        return userDAO.editUser(user);
    }

    public OperationResult deleteUser(User user) {
        return userDAO.deleteUser(user);
    }
}
