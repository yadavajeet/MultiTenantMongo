package com.ongraph.service.impl;

import com.ongraph.controller.BaseResponse;
import com.ongraph.dao.IUserDAO;
import com.ongraph.dao.User;
import com.ongraph.dto.UserDTO;
import com.ongraph.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveUser(UserDTO userDTO) {
		userDAO.save(copyFromDTO(userDTO));
	}

	@Override
	public UserDTO getUser(String loginName) throws Exception {
		System.out.println("Database used ********** ---------->>>>>>>>>> " + mongoTemplate.getDb().getName());
		User user = userDAO.getByLoginName(loginName);
		if(user == null) {
			throw new  Exception("User not found with loginName: " + loginName);
		}
		return copyToDTO(user);
	}

	@Override
	public void updateUser(UserDTO userDTO, BaseResponse response) throws Exception {
		User user = userDAO.update(copyFromDTO(userDTO));
		if(user == null) {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessage("No such user exist.");
			return;
		}
		response.setStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("User updated successfully");
	}

	@Override
	public void deleteUser(String loginName) throws Exception {
		User user = userDAO.getByLoginName(loginName);
		if(user == null) {
			throw new  Exception("User not found with loginName: " + loginName);
		}
		userDAO.deleteByLoginName(loginName);
	}
	
	private User copyFromDTO(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}
	
	private UserDTO copyToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

}
