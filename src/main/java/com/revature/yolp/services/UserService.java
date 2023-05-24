package com.revature.yolp.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.models.Role;
import com.revature.yolp.models.User;

import lombok.AllArgsConstructor;

/**
 * The UserService class handles user-related operations in the Yolp
 * Application.
 */
@AllArgsConstructor
public class UserService {
    // UserDAO field
    private final UserDAO userDao;
    private final RoleService roleService;

    /**
     * Registers a new user with the specified username and password.
     *
     * @param username the username for the new user
     * @param password the password for the new user
     * @return the newly registered User object
     */
    public User register(String username, String password) {
        Role foundRole = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed, foundRole.getId());
        userDao.save(newUser);
        return newUser;
    }

    /**
     * Checks if the specified username is valid.
     *
     * @param username the username to be checked
     * @return true if the username is valid, false otherwise
     */
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    /**
     * Checks if the specified username is unique.
     *
     * @param username the username to be checked
     * @return true if the username is unique, false otherwise
     */
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userDao.findByUsername(username);

        return userOpt.isEmpty();
    }

    /**
     * Checks if the specified password is valid.
     *
     * @param password the password to be checked
     * @return true if the password is valid, false otherwise
     */
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    /**
     * Checks if the specified password and confirm password match.
     *
     * @param password        the password to be compared
     * @param confirmPassword the confirm password to be compared
     * @return true if the passwords match, false otherwise
     */
    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
