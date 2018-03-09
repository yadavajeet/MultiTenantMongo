package com.ongraph.controller;

import com.ongraph.dao.User;
import com.ongraph.dto.RoleDTO;
import com.ongraph.response.BaseResponse;
import com.ongraph.response.DataResponse;
import com.ongraph.service.IUserService;
import com.ongraph.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value="/v1/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/{loginName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<? extends Object> getUser(@PathVariable String loginName) {
		//TenantContext.setTenant("default");
		try {
			return new ResponseEntity<UserDTO>(userService.getUser(loginName), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			BaseResponse response = new BaseResponse();
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage(e.getMessage());
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/update/{id}", produces = "application/json")
	public BaseResponse updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id, HttpServletResponse resp) {
			BaseResponse response = new BaseResponse();
		try {
			userDTO.setUserId(id);
			userService.updateUser(userDTO, response);
		}catch (Exception e){
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage("Something went wrong, please try again");
		}
		return response;
	}

	@GetMapping(value= "/getalluserswithrole/{roleId}", produces = "application/json")
	public DataResponse getAllUsersWithRole(@PathVariable Integer roleId){

		DataResponse response = new DataResponse();

		try {
			userService.getUsersByRoleId(roleId,response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage("Something went wrong, please try again");
		}
		return response;
	}

	@PostMapping(value= "/updaterole/{loginName}")
	public BaseResponse updateUserRole(@RequestBody RoleDTO roleDTO, @PathVariable String loginName) {
		BaseResponse response = new BaseResponse();
		UserDTO userDTO = null;
		try {
			userDTO = userService.getUser(loginName);
		} catch(Exception e) {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessage(e.getMessage());
			return response;
		}
		try {
			userDTO = userService.updateUserRole(roleDTO, userDTO);
			response.setStatus(HttpStatus.OK);
			response.setSuccess(true);
			response.setMessage("Details Updated Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@GetMapping(value="/get/getallusers")
	public DataResponse getAllUsers() {
		DataResponse response = new DataResponse();
		List<UserDTO> users = userService.getAllUsers();
		if(users.size() == 0 || users == null){
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessage("Data not exist in DB");
			return response;
		}
		response.setData(users);
		response.setSuccess(true);
		response.setMessage("Users found successfully");
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@GetMapping(value="/get/getuser", produces = "application/json")
	public DataResponse getUser() {
		DataResponse response= new DataResponse();
		UserDTO user = null;
		try {
			user = userService.getUser("pramod123");
			response.setData(user);
			response.setStatus(HttpStatus.OK);
			response.setSuccess(true);
			response.setMessage("Success");
		}catch(Exception e) {
			e.printStackTrace();
			response.setMessage("not success");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
