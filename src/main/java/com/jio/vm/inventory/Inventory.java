package com.jio.vm.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class Inventory {
 
    @Autowired
    //private InventoryService  customerService;
  
    @RequestMapping("/inventory") 
    @ResponseBody
    public String welcome() {
		return "Welcome to Inventory";    	

    }
}
