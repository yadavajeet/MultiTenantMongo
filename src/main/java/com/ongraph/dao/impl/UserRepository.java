package com.ongraph.dao.impl;

import java.util.List;

import com.ongraph.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
	
	List<User> findByFirstName(String loginName);

	// uses Mongo repository findOneBy<attribute name of the object, here 'User object'>
	User findOneByLoginName(String loginName);
	
	@Query("{ 'loginName' : ?0 }")
	User getByLoginName(String loginName);
	
	void deleteByLoginName(String loginName);

	@Override
	List<User> findAll();
}
