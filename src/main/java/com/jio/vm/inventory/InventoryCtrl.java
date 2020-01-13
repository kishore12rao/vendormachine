package com.jio.vm.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jio.models.ResponseModel;


@RestController
@RequestMapping(value="/inventory")
public class InventoryCtrl {

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value="/*")
	@ResponseBody
	public String welcome() {
		return "Welcome to Inventory, Provide valid Request";    	
	}    

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel add(@RequestParam int noOfProd) {
		return inventoryService.addInventory(noOfProd);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseModel update(@RequestParam int noOfProd) {
		return inventoryService.updateInventory(noOfProd);
	}

}
