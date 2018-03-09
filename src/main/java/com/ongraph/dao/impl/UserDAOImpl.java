package com.ongraph.dao.impl;

import java.util.List;

import com.ongraph.dao.IUserDAO;
import com.ongraph.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public List<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	@Override
	public User findOneByLoginName(String loginName) {
		return userRepository.findOneByLoginName(loginName);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteByLoginName(String loginName) {
		userRepository.deleteByLoginName(loginName);
	}

	@Override
	public User getByLoginName(String loginName) {
		return userRepository.getByLoginName(loginName);
	}

	@Override
	public User update(User user) {
		User _userInDB = userRepository.findOneByLoginName(user.getLoginName());
		_userInDB.setFirstName(user.getFirstName());
		_userInDB.setLastName(user.getLastName());
		_userInDB.setEmail(user.getEmail());
		return userRepository.save(_userInDB);
	}

	@Override
	public User updateRole(User user) {
		User dbUser = userRepository.findOneByLoginName(user.getLoginName());
		dbUser.setRole(user.getRole());
		return userRepository.save(dbUser);
	}
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getUsersByRoleId(Integer roleId){
		List<User> users = null;
		users = mongoOperations.find(new Query().addCriteria(Criteria.where("role.roleId").is(roleId)), User.class);
		return users;
	}

	protected UserRepository getUserRepository() {
		return this.userRepository;
	}
}
