package com.ongraph.controller;

import com.ongraph.service.IUserService;
import com.ongraph.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
	
}
