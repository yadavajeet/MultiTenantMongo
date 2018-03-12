package com.ongraph.controller;

import com.ongraph.dao.Role;
import com.ongraph.response.DataResponse;
import com.ongraph.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping(value="/getall")
    public DataResponse getRoles(){
        System.out.println("Inside get all role method");
        DataResponse response = new DataResponse();
        try {
            List<Role> roles = roleService.getAllRoles();
            if (roles.size() == 0) {
                response.setStatus(HttpStatus.NO_CONTENT);
                response.setMessage("No Roles Exist");
                return response;
            }
            response.setMessage("Roles found successfully");
            response.setStatus(HttpStatus.OK);
            response.setSuccess(true);
            response.setData(roles);
        } catch(Exception e){
            response.setMessage("Something went wrong");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
