package com.ongraph.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import com.ongraph.TenantContext;
import org.junit.Before;
import org.junit.Test;
import com.ongraph.base.BaseSpringTest;
import com.ongraph.dao.IUserDAO;
import com.ongraph.dao.User;
import com.ongraph.dao.impl.UserDAOImplTest;
import com.ongraph.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseSpringTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IUserDAO userDAO;
	
	@Before
	public void init() {
		Assert.notNull(userDAO);
		Query query = new Query();
		query.addCriteria(Criteria.where("loginName").regex(".*"));
		mongoTemplate.remove(query, User.class);
	}

	@Test
	public void testGet() throws Exception {
		String firstName = "Pavan";
		String middleName = "Vasant";
		String loginName = "P1_" + UUID.randomUUID();
		
		// Tenant1
		String tenantCode = "tenant1";		
		TenantContext.setTenant(tenantCode);
		userDAO.save(UserDAOImplTest.getUser(loginName + "_" + tenantCode, firstName + "_" + tenantCode, middleName  + "_" + tenantCode));
		try {
			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.get("/v1/user/" + loginName + "_" + tenantCode + "?tenantCode=" + tenantCode).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
			String content = result.getResponse().getContentAsString();
			System.out.println("Response ---> " + content);
			ObjectMapper om = new ObjectMapper();
			UserDTO userDTO = om.readValue(content, UserDTO.class);
			Assert.notNull(userDTO);
			Assert.isTrue((loginName + "_" + tenantCode).equals(userDTO.getLoginName()));
		} finally {
			userDAO.deleteByLoginName(loginName + "_" + tenantCode);
		}
		
		
		// Tenant2
		tenantCode = "tenant2";
		TenantContext.setTenant(tenantCode);
		userDAO.save(UserDAOImplTest.getUser(loginName + "_" + tenantCode, firstName + "_" + tenantCode, middleName  + "_" + tenantCode));
		try {
			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.get("/v1/user/" + loginName + "_" + tenantCode + "?tenantCode=" + tenantCode).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
			String content = result.getResponse().getContentAsString();
			System.out.println("Response ---> " + content);
			ObjectMapper om = new ObjectMapper();
			UserDTO userDTO = om.readValue(content, UserDTO.class);
			Assert.notNull(userDTO);
			Assert.isTrue((loginName + "_" + tenantCode).equals(userDTO.getLoginName()));
		} finally {
			userDAO.deleteByLoginName(loginName + "_" + tenantCode);
		}
	}

}
