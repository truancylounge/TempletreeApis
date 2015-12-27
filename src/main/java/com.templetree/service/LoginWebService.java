package com.templetree.service;

import com.templetree.dao.intf.LoginDaoIntf;
import com.templetree.exception.ExceptionMessages;
import com.templetree.exception.TempletreeException;
import com.templetree.model.Role;
import com.templetree.model.User;
import com.templetree.service.intf.LoginWebServiceIntf;
import com.templetree.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
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
    public User authenticateUser(User user) throws TempletreeException {
        User userDb =  loginDao.getUserCredentials(user.getUsername());
        if (userDb == null) {
            throw new TempletreeException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "TEST3", ExceptionMessages.USER_NOT_FOUND,
                    ExceptionMessages.USER_NOT_FOUND);
        }
        if (EncryptionUtil.getHash(user.getPassword()).equals(userDb.getPassword())
                /*&& user.getRole() == userDb.getRole() */) {
            user.setAuthenticated(Boolean.TRUE);
            user.setRole(userDb.getRole());
        } else {
            user.setAuthenticated(Boolean.FALSE);
        }

        return user;
    }

    @Override
    public User insertUser(User user) {
        user.setCreatedDate(new Timestamp(new Date().getTime()));
        user.setUpdatedDate(new Timestamp(new Date().getTime()));
        user.setPassword(EncryptionUtil.getHash(user.getPassword()));
        return loginDao.insertUser(user);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(EncryptionUtil.getHash(user.getPassword()));
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
