package com.revature.yolp.services;

import java.util.Optional;

import com.revature.yolp.daos.RoleDAO;
import com.revature.yolp.models.Role;
import com.revature.yolp.utils.custom_exceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
    private final RoleDAO roleDao;

    public Role findByName(String name) {
        Optional<Role> roleOpt = roleDao.findByName(name);

        if (roleOpt.isEmpty()) {
            throw new RoleNotFoundException();
        }

        return roleOpt.get();
    }
}
