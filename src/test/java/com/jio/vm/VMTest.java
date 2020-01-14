package com.jio.vm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class VMTest extends ApplicationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	
	public void setUpAdmin() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
		Authentication authToken = new UsernamePasswordAuthenticationToken ("admin", "admin", grantedAuthorities );
        SecurityContextHolder.getContext().setAuthentication(authToken);
	}
	
	public void setUpService() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("service"));
		Authentication authToken = new UsernamePasswordAuthenticationToken ("admin", "admin", grantedAuthorities );
        SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	@Test
	public void getAllVM() throws Exception {
		setUpAdmin();
		mockMvc.perform(get("/vendingmachine")).andExpect(status().isOk());				

	}
	
	@Test
	public void getVMById() throws Exception {
		setUpAdmin();
		mockMvc.perform(get("/vendingmachine/1")).andExpect(status().isOk());				

	}
	
	@Test
	public void getVMReport() throws Exception {
		setUpAdmin();
		mockMvc.perform(get("/vendingmachine/report")).andExpect(status().isOk());				

	}
		
	
	
	
	
	//API METRICS HEALTH
	@Test
	public void getmetrics() throws Exception {
		mockMvc.perform(get("/metrics")).andExpect(status().isOk());				

	}
	
	@Test
	public void gethealth() throws Exception {
		mockMvc.perform(get("/health")).andExpect(status().isOk());				

	}
	
	@Test
	public void getlogpath() throws Exception {
		mockMvc.perform(get("/logpath")).andExpect(status().isOk());				

	}
	
	
	
				

}
