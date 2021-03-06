package edu.java.web.dao;

import edu.java.web.beans.User;
import edu.java.web.status.OperationResult;

import java.util.List;

/**
 * Created by edwin on 22/01/17.
 */
public interface UserDAO  {

    public OperationResult getAllUsers();

    public OperationResult getUser(String userName);

    public OperationResult addUser(User user);

    public OperationResult editUser(User user);

    public OperationResult deleteUser(User user);

}
