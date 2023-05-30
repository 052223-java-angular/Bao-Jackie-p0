package com.revature.yolp.utils.custom_exceptions;

/**
 * The RoleNotFoundException is an exception thrown when a role is not found.
 */
public class RoleNotFoundException extends RuntimeException {
    /**
     * Constructs a new RoleNotFoundException with a default error message.
     */
    public RoleNotFoundException() {
        super("Role not found!");
    }
}
