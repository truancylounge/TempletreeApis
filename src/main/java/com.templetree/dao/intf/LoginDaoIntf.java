package com.templetree.dao.intf;

import com.templetree.model.Role;
import com.templetree.model.User;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface LoginDaoIntf {
    public User getUserCredentials(String username);
    public User updateUser(User user);
    public User insertUser(User user);
    public List<User> retrieveAllUsers();
    public void deleteUser(Integer id);
    public User getUserById(Integer id);
    public List<Role> getAllRoles();

}
