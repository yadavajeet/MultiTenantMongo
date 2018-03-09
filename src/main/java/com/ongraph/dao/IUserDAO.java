package com.ongraph.dao;

import java.util.List;

public interface IUserDAO {
	
	List<User> findByFirstName(String firstName);

	User findOneByLoginName(String loginName);

	User save(User user);

	void deleteByLoginName(String loginName);

	User getByLoginName(String loginName);

	User update(User user);

	User updateRole(User user);

	void delete(User user);

	List<User> getAllUsers();

	List<User> getUsersByRoleId(Integer roleId);

}
