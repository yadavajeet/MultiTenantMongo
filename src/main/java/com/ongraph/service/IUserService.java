package com.ongraph.service;

import com.ongraph.response.BaseResponse;
import com.ongraph.dto.RoleDTO;
import com.ongraph.dto.UserDTO;
import com.ongraph.response.DataResponse;

import java.util.List;

public interface IUserService {

	void saveUser(UserDTO userDTO) throws Exception;
	UserDTO getUser(String loginName) throws Exception;
	void updateUser(UserDTO userDTO, BaseResponse response) throws Exception;
	void deleteUser(String loginName) throws Exception;
	UserDTO updateUserRole(RoleDTO roleDTO, UserDTO userDTO) throws Exception;
	List<UserDTO> getAllUsers();
	void getUsersByRoleId(Integer roleId, DataResponse response) throws Exception;
}
