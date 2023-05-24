package com.revature.yolp.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.models.Role;
import com.revature.yolp.models.User;

public class UserService {
    // this is a field
    private final UserDAO userDao;
    private final RoleService roleService;

    // dependency inject
    public UserService(UserDAO userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    public User register(String username, String password) {
        Role foundFound = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, foundFound.getId());
        userDao.save(newUser);
        return newUser;
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userDao.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
