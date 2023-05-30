package com.revature.yolp.services;

import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.Assert.*;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.models.Role;
import com.revature.yolp.models.User;

public class UserServiceTest {

    @Mock
    private UserDAO userDao;
    @Mock
    private RoleService roleService;

    private UserService userService;

    @Before
    public void setUp() {
        // Initialize the Mockito framework
        MockitoAnnotations.openMocks(this);

        // Create a new instance of the UserService class with the mocked dependencies
        userService = new UserService(userDao, roleService);
    }

    @Test
    public void testRegister() {
        // Define the test input values
        String username = "testUser";
        String password = "testPassword";
        Role role = new Role("cd7a196a-b4a1-4f2a-a6fc-902cc887ab71", "USER");
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role.getId());

        // Mock the behavior of the roleService and userDao objects
        when(roleService.findByName("USER")).thenReturn(role);
        doNothing().when(userDao).save(any(User.class));

        // Call the register method of the userService object with the test input values
        User result = userService.register(username, password);

        // Verify that the userDao.save method was called once with any User object as
        // an argument
        verify(userDao, times(1)).save(any(User.class));

        // Verify that the result object has the expected username value
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testIsValidUsername() {
        String validUsername = "validUser";
        String invalidUsername = "";

        assertTrue(userService.isValidUsername(validUsername));
        assertFalse(userService.isValidUsername(invalidUsername));
    }

    @Test
    public void testIsUniqueUsername() {
        String existingUsername = "existingUser";
        String newUsername = "newUser";

        when(userDao.findByUsername(existingUsername)).thenReturn(Optional.of(new User()));
        when(userDao.findByUsername(newUsername)).thenReturn(Optional.empty());

        assertFalse(userService.isUniqueUsername(existingUsername));
        assertTrue(userService.isUniqueUsername(newUsername));
    }

    @Test
    public void testIsValidPassword() {
        String validPassword = "Valid123";
        String invalidPassword = "invalid";

        assertTrue(userService.isValidPassword(validPassword));
        assertFalse(userService.isValidPassword(invalidPassword));
    }

    @Test
    public void testIsSamePassword() {
        String password = "password123";
        String confirmPassword = "password123";
        String differentPassword = "differentPassword123";

        assertTrue(userService.isSamePassword(password, confirmPassword));
        assertFalse(userService.isSamePassword(password, differentPassword));
    }
}
