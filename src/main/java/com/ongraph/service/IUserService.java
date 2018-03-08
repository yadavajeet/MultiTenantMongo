package com.ongraph.service;

import com.ongraph.controller.BaseResponse;
import com.ongraph.dto.UserDTO;

public interface IUserService {

	void saveUser(UserDTO userDTO) throws Exception;
	UserDTO getUser(String loginName) throws Exception;
	void updateUser(UserDTO userDTO, BaseResponse response) throws Exception;
	void deleteUser(String loginName) throws Exception;
}
