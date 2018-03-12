package com.ongraph.dao.impl;

import com.ongraph.dao.Role;
import com.ongraph.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {
    @Override
    List<Role> findAll();
}
