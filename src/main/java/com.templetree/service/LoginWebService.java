package com.templetree.service;

import com.templetree.dao.intf.LoginDaoIntf;
import com.templetree.model.Role;
import com.templetree.model.User;
import com.templetree.service.intf.LoginWebServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Lalith Mannur
 */

@Service("loginWebService")
@Transactional
public class LoginWebService implements LoginWebServiceIntf {

    @Autowired
    private LoginDaoIntf loginDao;

    @Override
    public User authenticateUser(User user) {
        return loginDao.getUserCredentials(user.getUsername());
    }

    @Override
    public User insertUser(User user) {
        user.setCreatedDate(new Timestamp(new Date().getTime()));
        user.setUpdatedDate(new Timestamp(new Date().getTime()));
        return loginDao.insertUser(user);
    }

    @Override
    public User updateUser(User user) {
        return loginDao.updateUser(user);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return loginDao.retrieveAllUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return loginDao.getUserCredentials(username);
    }

    @Override
    public void deleteUser(Integer id) {
        loginDao.deleteUser(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }
}
