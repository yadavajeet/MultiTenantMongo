package com.ongraph.dao.impl;

import com.ongraph.dao.IRoleDAO;
import com.ongraph.dao.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements IRoleDAO {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
