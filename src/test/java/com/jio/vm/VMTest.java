package com.jio.vm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jio.vm.vendingmachine.VendingMachine;

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

	//ADMIN 
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

	@Test
	public void addInventory() throws Exception {
		setUpAdmin();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inventory?noOfProd=1")
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON));

	}

	@Test
	public void addVM() throws Exception {
		setUpAdmin();
		
		VendingMachine vm = new VendingMachine();
		vm.setName("Vm Machine new");
		vm.setDescription("VM ADD TEST DESCRIPTION");
		vm.setItemCount(20);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/vendingmachine")
		  .content(vm.toJSON().toString())
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON));

	}
	
	
	@Test
	public void updateVMProps() throws Exception {
		setUpAdmin();
		
		VendingMachine vm = new VendingMachine();
		vm.setName("Vm Machine update");
		vm.setDescription("VM ADD TEST DESCRIPTION");
		
		mockMvc.perform(MockMvcRequestBuilders.put("/vendingmachine")
		  .content(vm.toJSON().toString())
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON));

	}

	@Test
	public void deleteVM() throws Exception {
		setUpAdmin();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/vendingmachine/3")
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON));
	}
	
	
	//SERVICE PERSON 
	
	@Test
	public void refillVM() throws Exception {
		setUpAdmin();
		
		VendingMachine vm = new VendingMachine();
		vm.setItemRefilled(20);
		vm.setMoneyCollected(5000);
		
		mockMvc.perform(MockMvcRequestBuilders.patch("/vendingmachine/4")
		  .content(vm.toJSON().toString())
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON));

	}
	
	
	@Test
	public void updateInventory() throws Exception {
		setUpAdmin();
		
		mockMvc.perform(MockMvcRequestBuilders.put("/inventory?noOfProd=3")
		  .contentType(MediaType.APPLICATION_JSON)
		  .accept(MediaType.APPLICATION_JSON));
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
