package com.jio.vm.inventory;

import com.jio.models.ResponseModel;

public interface InventoryDAO {
	
	ResponseModel addInventory(int noOfProd);
	
	ResponseModel updateInventory(int noOfProd);

	boolean checkInventoryExists();

}
