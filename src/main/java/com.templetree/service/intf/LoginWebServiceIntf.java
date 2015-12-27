package com.templetree.service.intf;

import com.templetree.exception.TempletreeException;
import com.templetree.model.Role;
import com.templetree.model.User;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface LoginWebServiceIntf {
    public User authenticateUser(User user) throws TempletreeException;
    public User insertUser(User user);
    public User updateUser(User user);
    public List<User> retrieveAllUsers();
    public User getUserByUsername(String username);
    public void deleteUser(Integer id);
    public List<Role> getAllRoles();
}
