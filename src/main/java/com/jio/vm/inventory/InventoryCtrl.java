package com.jio.vm.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jio.models.ResponseModel;
import com.jio.vm.vendingmachine.VMService;


@RestController
@RequestMapping(value="/inventory")
public class InventoryCtrl {

	@Autowired
    private VMService vmservice;
    
	@RequestMapping(value="/*")
	@ResponseBody
	public String welcome() {
		return "Welcome to Inventory, Provide valid Request";    	

	}    

	@RequestMapping(value="/{vmid}",method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseModel refill(@PathVariable(value="vmid") final int vmid,@RequestBody VMRefilOnly refil) {
		System.out.println("VMID:: "+vmid +" refill :: "+refil.toJSON());
		return vmservice.refillOnly(vmid,refil);
	}

}
