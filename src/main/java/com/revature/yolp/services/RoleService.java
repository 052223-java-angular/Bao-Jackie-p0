package com.revature.yolp.services;

import java.util.Optional;

import com.revature.yolp.daos.RoleDAO;
import com.revature.yolp.models.Role;
import com.revature.yolp.utils.custom_exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

/**
 * The RoleService class handles role-related operations in the Yolp
 * Application.
 */
@AllArgsConstructor
public class RoleService {
    private final RoleDAO roleDao;

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role to find
     * @return the Role object with the specified name
     * @throws RoleNotFoundException if the role with the specified name is not
     *                               found
     */
    public Role findByName(String name) throws RoleNotFoundException {
        Optional<Role> roleOpt = roleDao.findByName(name);

        return roleOpt.orElseThrow(RoleNotFoundException::new);
    }
}
