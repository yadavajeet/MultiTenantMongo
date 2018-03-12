package com.ongraph.service.impl;

import com.ongraph.dao.IRoleDAO;
import com.ongraph.dao.Role;
import com.ongraph.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleDAO roleDAO;

    public List<Role> getAllRoles(){
        return roleDAO.findAllRoles();
    }
}
